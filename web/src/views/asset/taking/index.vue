<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
        <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input
          v-model="queryParams.assetName"
          placeholder="请输入资产名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库" prop="houseId">
          <el-select v-model="queryParams.houseId" filterable placeholder="请选择仓库">
            <el-option
              v-for="item in warehouseList"
              :key="item.id"
              :label="item.title"
              :value="item.id">
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="使用人" prop="userId">
          <el-select v-model="queryParams.useUserId" filterable placeholder="请输入姓名选择" :loading="loading">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="归属部门" prop="depttId">
          <treeselect v-model="queryParams.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门"  style="width:240px"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['assets:taking:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['assets:taking:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['assets:taking:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="takingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资产分类" align="center" prop="cateName" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="库存数量" align="center" prop="amount" />
      <el-table-column label="仓库" align="center" prop="houseName" />
      <el-table-column label="使用人" align="center" prop="userName" />
      <el-table-column label="归属部门" align="center" prop="deptName" />
      <el-table-column label="照片" align="center" prop="imgId">
        <template slot-scope="scope">
          <span v-html="getDownLink(scope.row)"></span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="盘点时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['assets:taking:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资产盘点对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
          <treeselect v-model="form.cateId" :options="categoryOptions" @input="remoteAsset" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
        </el-form-item>
        <el-form-item label="耗材" prop="materialId" v-if="this.form.type == '2'">
          <el-select v-model="form.materialId" filterable @change="changeMateria"  placeholder="请输入名称选择" :loading="loading">
            <el-option
              v-for="item in materialList"
              :key="item.id"
              :label="item.name + ' 库存:' + item.amount"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资产编号" prop="assetId" v-if="this.form.type == '1'">
          <el-select v-model="form.assetId" filterable @change="changeAsset" placeholder="请输入编号选择" :loading="loading">
            <el-option
              v-for="item in assetList"
              :key="item.id"
              :label="item.name + '' + item.num"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="库存数量" prop="amount" v-if="this.form.type == '2'">
          <el-input v-model="form.amount" placeholder="请输入库存数量" />
        </el-form-item>
        <el-form-item label="仓库" prop="houseId">
          <el-select v-model="form.houseId" filterable placeholder="请选择仓库">
            <el-option
              v-for="item in warehouseList"
              :key="item.id"
              :label="item.title"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="归属部门" prop="deptId" v-if="this.form.type == '1'">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="使用人" prop="userId" v-if="this.form.type == '1'">
          <el-select v-model="form.useUserId" filterable placeholder="请输入姓名选择" :loading="loading">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="照片" prop="imgId">
          <imageUpload v-model="form.imgId" :file="form.img"/>
        </el-form-item>
        <el-form-item label="状态" v-if="this.form.type == '1'">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="盘点说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入盘点说明" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTaking, getTaking, delTaking, addTaking, updateTaking, exportTaking } from "@/api/asset/taking";
import { listInfo } from "@/api/asset/info";
import { listMaterial} from "@/api/asset/material";
import { listCategory } from "@/api/asset/category";
import { selectWarehouse } from "@/api/asset/warehouse";
import { selectUser } from "@/api/sys/public";
import { treeselect } from "@/api/system/dept";

import ImageUpload from '@/components/ImageUpload';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Taking",
  components: {
    Treeselect,ImageUpload
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 资产盘点表格数据
      takingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
      // 资产分类树选项
      categoryOptions: [],
      categoryList: [],
      warehouseList: [],
      assetList: [],
      materialList:[],
      // 用户数据
      userList: [],
      deptOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        materialId: null,
        assetId: null,
        amount: null,
        houseId: null,
        userId: null,
        depttId: null,
        status: null,
        assetName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("asset_status").then(response => {
      this.statusOptions = response.data;
    });
    selectWarehouse({status:'1'}).then(response => {
      this.warehouseList = response.rows;
    });
    this.getTreeselect();
    treeselect().then(response => {
      this.deptOptions = response.data;
    });
    selectUser({status:'0'}).then(response => {
      this.userList = response.rows;
    });
  },
  methods: {
    /** 查询资产盘点列表 */
    getList() {
      this.loading = true;
      listTaking(this.queryParams).then(response => {
        this.takingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 转换资产分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.title,
        children: node.children
      };
    },
  /** 查询部门下拉树结构 */
    getTreeselect() {
      listCategory().then(response => {
        this.categoryOptions = [];
        const data = { id: 0, title: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.categoryOptions.push(data);
        this.categoryList = response.data;
      });
    },
    /** 下载附件链接 */
    getDownLink(row) {
      if(row.img && row.img.id){
        return '<a href="'+process.env.VUE_APP_BASE_API+'/sys/files/download/'+row.img.id+'" target="_blank">'+row.img.title+'</a>';
      }
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    //加载资产或者耗材
    remoteAsset() {
      this.form.houseId = null;
      this.form.amount = null;
      this.form.status = null;
      this.form.assetId = null;
      this.form.materialId = null;
      this.form.type = null;
      for(var i=0;i<this.categoryList.length;i++){
        if(this.categoryList[i]['id'] == this.form.cateId){          
          //console.log(this.categoryList[i]);
          this.form.type = this.categoryList[i]['type'];
          break;
        }
      }
      //console.log(this.form.cateId+":"+this.form.type)
      if(this.form.type == '1'){
        listInfo({cateId:this.form.cateId}).then(response => {
          this.assetList = response.rows;
        });
      }else if(this.form.type == '2'){
        listMaterial({cateId:this.form.cateId}).then(response => {
          this.materialList = response.rows;
        });
      }
    },
    changeAsset() {
      for(var i=0;i<this.assetList.length;i++){
        if(this.assetList[i]['id'] == this.form.assetId){      
          this.form.deptId = this.assetList[i]['deptId'];   
          this.form.userId = this.assetList[i]['useUserId'];   
          this.form.houseId = this.assetList[i]['houseId'];
          this.form.status = this.assetList[i]['status'];
          break;        
        }
      }
    },
    changeMateria() {
      for(var i=0;i<this.materialList.length;i++){
        if(this.materialList[i]['id'] == this.form.materialId){  
          this.form.houseId = this.materialList[i]['houseId'];
          this.form.amount = this.materialList[i]['amount'];
          break;        
        }
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        cateId: null,
        materialId: null,
        assetId: null,
        amount: null,
        houseId: null,
        userId: null,
        deptId: null,
        imgId: null,
        status: "0",
        type: null,
        comment: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资产盘点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTaking(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资产盘点";

        this.remoteAsset();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTaking(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaking(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除资产盘点编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTaking(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有资产盘点数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTaking(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
