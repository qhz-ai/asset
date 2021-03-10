<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
     <el-form-item label="资产分类" prop="cateId">
       <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" style="width:240px"/>
     </el-form-item>
      <el-form-item label="租售类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择租售类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="负责人" prop="masterUserId">
        <el-input
          v-model="queryParams.masterUserId"
          placeholder="请输入负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租售时间" prop="startTime">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="是否完成" prop="isComplete">
        <el-input
          v-model="form.isComplete"
          placeholder="请输入是否完成"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否完成" prop="isComplete">
        <el-select v-model="queryParams.isComplete" placeholder="请选择是否完成" clearable size="small">
          <el-option
            v-for="dict in yesnoOptions"
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
          v-hasPermi="['asset:sales:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:sales:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="salesList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="耗材/耗材" align="center" prop="materialId">
        <template slot-scope="scope">
          <span v-if="scope.row.material">{{ scope.row.material.name }} </span>
          <span v-if="scope.row.aptitude">{{ scope.row.aptitude.name }} </span>
          <span v-if="scope.row.asset">{{ scope.row.asset.name + ' ' +  scope.row.asset.num}}</span>
        </template>
      </el-table-column>
      <el-table-column label="租售类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="数量" align="center" prop="amount" />
      <el-table-column label="总价" align="center" prop="price" />
      <el-table-column label="负责人" align="center" prop="masterUser.nickName" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否完成" align="center" prop="isComplete" :formatter="checkCompleteFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:sales:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:sales:remove']"
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

    <!-- 添加或修改资产租售对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
           <treeselect v-model="form.cateId" :options="categoryOptions" @input="remoteAsset" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
        </el-form-item>
        <el-form-item label="耗材" prop="materialId" v-if="cateType == '2'">
          <el-select v-model="form.materialId" filterable placeholder="请输入名称选择">
            <el-option
              v-for="item in materialList"
              :key="item.id"
              :label="item.name + ' 库存:' + item.amount"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资产编号" prop="assetId" v-if="cateType == '1'">
          <el-select v-model="form.assetId" filterable placeholder="请输入编号选择">
            <el-option
              v-for="item in assetList"
              :key="item.id"
              :label="item.name + '' + item.num"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="无形资产" prop="aptitudeId" v-if="cateType == '3'">
          <el-select v-model="form.aptitudeId" filterable placeholder="请输入名称选择">
            <el-option
              v-for="item in aptitudeList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="租售类型" prop="type" v-if="cateType != '2'">
          <el-select v-model="form.type" placeholder="请选择租售类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="amount" v-if="cateType == '2'">
          <el-input v-model="form.amount" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="总价" prop="price">
          <el-input v-model="form.price" placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="租售原由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入租售原由" />
        </el-form-item>
        <el-form-item label="负责人" prop="masterUserId">
          <el-select v-model="form.masterUserId" filterable placeholder="请输入姓名选择">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable size="small"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成时间" prop="finishTime">
          <el-date-picker clearable size="small"
            v-model="form.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否完成" prop="isComplete">
          <el-select v-model="form.isComplete" placeholder="请选择是否完成" clearable size="small">
            <el-option
              v-for="dict in yesnoOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
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
import { listSales, getSales, delSales, addSales, updateSales, exportSales } from "@/api/asset/sales";
import { listInfo } from "@/api/asset/info";
import { listMaterial} from "@/api/asset/material";
import { listAptitude } from "@/api/asset/aptitude";
import { selectUser } from "@/api/sys/public";
import { listCategory } from "@/api/asset/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "Sales",
  components: {
    Treeselect
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
      // 资产租售表格数据
      salesList: [],
      // 用户数据
      userList: [],
      // 资产分类树选项
      categoryOptions: [],
      categoryList: [],
      materialList: [],
      assetList: [],
      aptitudeList: [],
      cateType:'',
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 租售类型字典
      typeOptions: [],
      // 是否字典
      yesnoOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        materialId: null,
        assetId: null,
        type: null,
        masterUserId: null,
        startTime: null,
        finishTime: null,
        isComplete: null,
        orderByColumn:"t.id",
        isAsc:"DESC"
      },
      // 表单参数
      form: {},
      dateRange:[],
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "租售原由不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "总价不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sales_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.yesnoOptions = response.data;
    });
    selectUser({status:'0'}).then(response => {
      this.userList = response.rows;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询资产租售列表 */
    getList() {
      this.loading = true;
      listSales(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.salesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 租售类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 是否完成字典翻译
    checkCompleteFormat(row, column) {
      return this.selectDictLabel(this.yesnoOptions, row.isComplete);
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
        this.categoryList = response.data;
        this.categoryOptions = [];
        const data = { id: 0, title: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.categoryOptions.push(data);
      });
    },
    remoteAsset() {
      var cate = null;
      for(var i=0;i<this.categoryList.length;i++){
        console.log(this.categoryList[i]['id']+'  ' + this.form.cateId)
        if(this.categoryList[i]['id'] == this.form.cateId){
          cate = this.categoryList[i];
          break;
        }
      }
      if(cate == null){
        this.cateType = '';
        return;
      }
      this.cateType = cate.type;
      if(cate.type == '1'){
        listInfo({cateId:this.form.cateId}).then(response => {
          this.assetList = response.rows;
          this.form.amount =  1;
          this.form.materialId = null;
          this.form.aptitudeId = null;
        });
      }else if(cate.type == '2'){
        listMaterial({cateId:this.form.cateId}).then(response => {
          this.materialList = response.rows;
          this.form.assetId = null;
          this.form.aptitudeId = null;
          this.form.type = '1';
        });
      }else{
        listAptitude({cateId:this.form.cateId}).then(response => {
          this.aptitudeList = response.rows;
          this.form.amount =  1;
          this.form.materialId = null;
          this.form.assetId = null;
        });
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
        aptitudeId: null,
        type: null,
        amount: null,
        price: null,
        reason: null,
        masterUserId: null,
        startTime: null,
        finishTime: null,
        isComplete: 'N'
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
      this.title = "添加资产租售";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSales(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资产租售";

        this.remoteAsset();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSales(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSales(this.form).then(response => {
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
      this.$confirm('是否确认删除资产租售编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSales(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有资产租售数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSales(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
