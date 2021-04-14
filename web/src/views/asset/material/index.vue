<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
     <el-form-item label="资产分类" prop="cateId">
        <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
      </el-form-item>
      <el-form-item label="产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入产品名称"
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
      <el-form-item label="耗材状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择耗材状态" clearable size="small">
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
          v-hasPermi="['asset:material:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['asset:material:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:material:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="产品名称" align="center" prop="name" />
      <el-table-column label="厂商" align="center" prop="factory" />
      <el-table-column label="购买渠道" align="center" prop="proxy" />
      <el-table-column label="计量单位" align="center" prop="unit" />
      <el-table-column label="库存数量" align="center" prop="amount" />
      <el-table-column label="市场价格" align="center" prop="price1" />
      <el-table-column label="购买价格" align="center" prop="price2" />
      <el-table-column label="仓库" align="center" prop="house.title" />
      <el-table-column label="耗材状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:material:edit']"
          >修改</el-button>
          <el-button v-if="scope.row.status == '4'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:material:remove']"
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

    <!-- 添加或修改耗材信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
       <el-tabs type="card" >
        <el-tab-pane label="基本信息">
        <el-form-item label="资产分类" prop="cateId">
          <treeselect v-model="form.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类" />
        </el-form-item>
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="库存数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入库存数量" />
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入计量单位" />
        </el-form-item>
        <el-form-item label="购买价格" prop="price2">
          <el-input v-model="form.price2" placeholder="请输入购买价格" />
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
        <el-form-item label="耗材状态">
          <el-select v-model="form.status" placeholder="请选择耗材状态" clearable size="small">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="生成盘点记录" v-if="form.id">
          <el-checkbox @change="recordTaking">是</el-checkbox>
        </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="详细信息">
        <el-form-item label="厂商" prop="factory">
          <el-input v-model="form.factory" placeholder="请输入厂商" />
        </el-form-item>
        <el-form-item label="参数" prop="param">
          <el-input v-model="form.param" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="购买渠道" prop="proxy">
          <el-input v-model="form.proxy" placeholder="请输入购买渠道" />
        </el-form-item>
        <el-form-item label="市场价格" prop="price1">
          <el-input v-model="form.price1" placeholder="请输入市场价格" />
        </el-form-item>
        <el-form-item label="耗材说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入耗材说明" />
        </el-form-item>
        </el-tab-pane>
      </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

     <!-- 导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的耗材数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial, exportMaterial, importTemplate } from "@/api/asset/material";
import { selectWarehouse } from "@/api/asset/warehouse";
import { listCategory } from "@/api/asset/category";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import ImageUpload from '@/components/ImageUpload';
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Material",
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
      // 耗材信息表格数据
      materialList: [],
      warehouseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 耗材状态字典
      statusOptions: [],
      // 资产分类树选项
      categoryOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cateId: null,
        name: null,
        houseId: null,
        status: null,
        orderByColumn:"t.id",
        isAsc:"DESC"
      },
      // 表单参数
      form: {},
      // 导入参数
      upload: {
        // 是否显示弹出层
        open: false,
        // 弹出层标题
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/asset/material/importData"
      },
      // 表单校验
      rules: {
        cateId: [
          { required: true, message: "耗材分类不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        houseId: [
          { required: true, message: "仓库不能为空", trigger: "blur" }
        ],
        price2: [
          { required: true, message: "购买价格不能为空", trigger: "blur" }
        ],
        unit: [
          { required: true, message: "计量单位不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "库存数量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("material_status").then(response => {
      this.statusOptions = response.data;
    });
    selectWarehouse({status:'1'}).then(response => {
      this.warehouseList = response.rows;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询耗材信息列表 */
    getList() {
      this.loading = true;
      listMaterial(this.queryParams).then(response => {
        this.materialList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 耗材状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
      listCategory({type:'2'}).then(response => {
        this.categoryOptions = [];
        const data = { id: 0, title: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.categoryOptions.push(data);
      });
    },
    recordTaking(v) {
      this.form.params.taking = v;
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
        name: null,
        param: null,
        factory: null,
        proxy: null,
        unit: null,
        amount: null,
        price1: null,
        price2: null,
        houseId: null,
        comment: null,
        status: "1",
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
      this.title = "添加耗材信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMaterial(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改耗材信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMaterial(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaterial(this.form).then(response => {
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
      this.$confirm('是否确认删除选中的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMaterial(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有耗材信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMaterial(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "耗材导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg);
      });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
