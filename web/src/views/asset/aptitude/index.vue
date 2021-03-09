<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="cateId">
         <treeselect v-model="queryParams.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
       </el-form-item>
      <el-form-item label="资产名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入资产名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获取时间" prop="getTime">
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
          v-hasPermi="['asset:aptitude:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:aptitude:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aptitudeList">
      <el-table-column label="资产分类" align="center" prop="cate.title" />
      <el-table-column label="资产名" align="center" prop="name" />
      <el-table-column label="图片" align="center" prop="imgId">
        <template slot-scope="scope">
          <span v-html="getDownLink(scope.row)"></span>
        </template>
      </el-table-column>
      <el-table-column label="获取时间" align="center" prop="getTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.getTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资产说明" align="center" prop="comment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:aptitude:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:aptitude:remove']"
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

    <!-- 添加或修改无形资产对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="cateId">
           <treeselect v-model="form.cateId" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择分类"  style="width:240px"/>
         </el-form-item>
        <el-form-item label="资产名" prop="name">
          <el-input v-model="form.name" placeholder="请输入资产名" />
        </el-form-item>
        <el-form-item label="图片">
          <imageUpload v-model="form.imgId" :file="form.img"/>
        </el-form-item>
        <el-form-item label="获取时间" prop="getTime">
          <el-date-picker clearable size="small"
            v-model="form.getTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择获取时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="资产说明" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入资产说明" />
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
import { listAptitude, getAptitude, delAptitude, addAptitude, updateAptitude, exportAptitude } from "@/api/asset/aptitude";
import { listCategory } from "@/api/asset/category";
import Treeselect from "@riophae/vue-treeselect";
import ImageUpload from '@/components/ImageUpload';
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Aptitude",
  components: {
    ImageUpload,Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 无形资产表格数据
      aptitudeList: [],
      // 资产分类树选项
      categoryOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        cateId: null,
        getTime: null,
      },
      // 表单参数
      form: {},
      dateRange: [],
      // 表单校验
      rules: {
        name: [
          { required: true, message: "资产名不能为空", trigger: "blur" }
        ],
        cateId: [
          { required: true, message: "资产分类不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getTreeselect();
  },
  methods: {
    /** 查询无形资产列表 */
    getList() {
      this.loading = true;
      listAptitude(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.aptitudeList = response.rows;
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
      listCategory({'type':'3'}).then(response => {
        this.categoryOptions = [];
        const data = { id: 0, title: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.categoryOptions.push(data);
      });
    },
    /** 下载附件链接 */
    getDownLink(row) {
      if(row.img && row.img.id){
        return '<a href="'+process.env.VUE_APP_BASE_API+'/sys/files/download/'+row.img.id+'" target="_blank">'+row.img.title+'</a>';
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
        name: null,
        imgId: null,
        cateId: null,
        getTime: null,
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加无形资产";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAptitude(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改无形资产";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAptitude(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAptitude(this.form).then(response => {
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
      this.$confirm('是否确认删除无形资产编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAptitude(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有无形资产数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAptitude(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
