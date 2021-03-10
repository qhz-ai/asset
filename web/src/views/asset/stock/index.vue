<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年度" prop="year">
        <el-date-picker clearable size="small"
          v-model="queryParams.year"
          type="year"
          value-format="yyyy"
          placeholder="选择年度">
        </el-date-picker>
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
          v-hasPermi="['asset:stock:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:stock:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockList">
      <el-table-column label="年度" align="center" prop="year" width="180"/>
      <el-table-column label="资产总值" align="center" prop="worth" />
      <el-table-column label="固定资产总数" align="center" prop="assetCount" />
      <el-table-column label="固定资产总值" align="center" prop="assetWorth" />
      <el-table-column label="耗材总类" align="center" prop="materialCount" />
      <el-table-column label="耗材资产总值" align="center" prop="materialWorth" />
      <el-table-column label="报废资产总数" align="center" prop="scrapCount" />
      <el-table-column label="报废资产总值" align="center" prop="scrapWorth" />
      <el-table-column label="资产维护次数" align="center" prop="maintainCount" />
      <el-table-column label="采购次数" align="center" prop="purchaseCount" />
      <el-table-column label="采购总值" align="center" prop="purchaseWorth" />
      <el-table-column label="盘点说明" align="center" prop="comment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:stock:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleCount(scope.row)"
            v-hasPermi="['oa:msg:edit']"
          >计算</el-button>
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
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="年度" prop="year">
          <el-date-picker clearable size="small"
            v-model="form.year"
            type="year"
            value-format="yyyy"
            placeholder="选择年度">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="资产总值" prop="worth">
          <el-input v-model="form.worth" placeholder="请输入资产总值" />
        </el-form-item>
        <el-form-item label="固定资产总数" prop="assetCount">
          <el-input v-model="form.assetCount" placeholder="请输入固定资产总数" />
        </el-form-item>
        <el-form-item label="固定资产总值" prop="assetWorth">
          <el-input v-model="form.assetWorth" placeholder="请输入固定资产总值" />
        </el-form-item>
        <el-form-item label="耗材总类" prop="materialCount">
          <el-input v-model="form.materialCount" placeholder="请输入耗材总类" />
        </el-form-item>
        <el-form-item label="耗材资产总值" prop="materialWorth">
          <el-input v-model="form.materialWorth" placeholder="请输入耗材资产总值" />
        </el-form-item>
        <el-form-item label="报废资产总数" prop="scrapCount">
          <el-input v-model="form.scrapCount" placeholder="请输入报废资产总数" />
        </el-form-item>
        <el-form-item label="报废资产总值" prop="scrapWorth">
          <el-input v-model="form.scrapWorth" placeholder="请输入报废资产总值" />
        </el-form-item>
        <el-form-item label="资产维护次数" prop="maintainCount">
          <el-input v-model="form.maintainCount" placeholder="请输入资产维护次数" />
        </el-form-item>
        <el-form-item label="采购次数" prop="purchaseCount">
          <el-input v-model="form.purchaseCount" placeholder="请输入采购次数" />
        </el-form-item>
        <el-form-item label="采购总值" prop="purchaseWorth">
          <el-input v-model="form.purchaseWorth" placeholder="请输入采购总值" />
        </el-form-item>
        <el-form-item label="盘点说明" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容" />
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
import { listStock, getStock, countStock, addStock, updateStock, exportStock } from "@/api/asset/stock";

export default {
  name: "Stock",
  components: {
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
      stockList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        year: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        year: [
          { required: true, message: "年度不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新人不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资产盘点列表 */
    getList() {
      this.loading = true;
      listStock(this.queryParams).then(response => {
        this.stockList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        year: null,
        worth: null,
        assetCount: null,
        assetWorth: null,
        materialCount: null,
        materialWorth: null,
        scrapCount: null,
        scrapWorth: null,
        maintainCount: null,
        purchaseCount: null,
        purchaseWorth: null,
        comment: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        orderByColumn:"id",
        isAsc:"DESC"
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
      getStock(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资产盘点";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStock(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStock(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 计算按钮操作 */
    handleCount(row) {
      const ids = row.id || this.ids;
      this.$confirm('该操作会以当前数据状态的计算结果覆盖原来的数据，您确认要进行该操作吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return countStock(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("计算成功");
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
          return exportStock(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
