<template>
  <div class="app-container">

      <el-row :gutter="20">
        <!--部门数据-->
        <el-col :span="4" :xs="24">
          <h5>文件类型检索</h5>
              <el-menu
                :default-active="typeIndex"
                class="el-menu-vertical-demo"
                @select="handleType">
                <el-menu-item index="11">
                  <i class="el-icon-s-home"></i>
                  <span slot="title">主页</span>
                </el-menu-item>
                <el-menu-item index="0">
                  <i class="el-icon-menu"></i>
                  <span slot="title">全部文件</span>
                </el-menu-item>
                <el-menu-item index="1">
                  <i class="el-icon-document"></i>
                  <span slot="title">文档</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <i class="el-icon-picture"></i>
                  <span slot="title">图片</span>
                </el-menu-item>
                <el-menu-item index="3">
                  <i class="el-icon-camera"></i>
                  <span slot="title">音视频</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-files"></i>
                  <span slot="title">压缩包</span>
                </el-menu-item>
                <el-menu-item index="12">
                  <i class="el-icon-share"></i>
                  <span slot="title">共享</span>
                </el-menu-item>
              </el-menu>
        </el-col>
        <!--用户数据-->
        <el-col :span="20" :xs="24">

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
          <el-upload v-hasPermi="['yun:files:add']"
            ref="upload"
            :limit="1"
            accept=".doc, .docx, .pdf, .jpg, .jpeg, .png, .mp4, .avi, .wmv, .xls, .xlsx, .ppt, .pptx, .gif, .3gp, .flv, .mp3, .zip, .rar"
            :headers="upload.headers"
            :action="upload.url + '?updateSupport=' + upload.updateSupport"
            :disabled="upload.isUploading"
            :on-progress="handleFileUploadProgress"
            :on-success="handleFileSuccess">
            <el-button size="mini" type="primary" icon="el-icon-upload">上传文件</el-button>
          </el-upload>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAddFolder"
              v-hasPermi="['yun:folder:add']"
            >创建目录</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-if="queryParams.type != '12'"
              type="warning"
              plain
              icon="el-icon-share"
              size="mini"
              :disabled="multiple"
              @click="handleShare"
              v-hasPermi="['yun:files:add']"
            >共享</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button v-if="queryParams.type != '12'"
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['yun:files:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5" v-if="paths.length > 0">
            <el-button
              type="info"
              plain
              icon="el-icon-top"
              size="mini"
              @click="handleUpFolder"
            >返回</el-button>
          </el-col>
          <el-col :span="12">
            <el-breadcrumb v-if="paths.length > 0" separator="/" style="line-height: 2;">
              <el-breadcrumb-item >当前目录:</el-breadcrumb-item>
              <el-breadcrumb-item v-for="(item, i) in paths">{{ item.title }}</el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
        </el-row>



    <el-table v-loading="loading" :data="filesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件" align="center" prop="title">
        <template slot-scope="scope">
          <el-link icon="el-icon-folder" v-if="scope.row.type == 'folder'" @click="clickFolder(scope.row)">{{ scope.row.title}}</el-link>
          <el-link v-if="scope.row.type != 'folder'" @click="clickFile(scope.row)">{{ scope.row.title}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">

        <template slot="header" slot-scope="scope">
          <el-input
            v-model="queryParams.title"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['yun:files:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['yun:files:edit']"
          >重命名</el-button>
          <el-button v-if="scope.row.isPublic == 'N'"
            size="mini"
            type="text"
            icon="el-icon-share"
            @click="handleShare(scope.row)"
          >共享</el-button>
          <el-button v-if="scope.row.isPublic == 'Y'"
            size="mini"
            type="text"
            icon="el-icon-share"
            @click="handleShare(scope.row)"
          >取消共享</el-button>
        </template>
      </el-table-column>
    </el-table>

      </el-col>
    </el-row>
    <!-- 添加或修改云盘文件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="目录" prop="folderId">
          <el-input v-model="form.folderId" placeholder="请输入目录" />
        </el-form-item>
        <el-form-item label="文件" prop="fileId">
          <el-input v-model="form.fileId" placeholder="请输入文件" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
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
import { listMyFiles, listShareFiles, getFiles, delFiles, addFiles, updateFiles, shareFiles, exportFiles } from "@/api/yun/files";
import { listMyFolder, getFolder, delFolder, addFolder, updateFolder, exportFolder } from "@/api/yun/folder";
import { listShare, getShare, delShare, addShare, updateShare, exportShare } from "@/api/yun/share";
import FileUpload from '@/components/FileUpload';
import { getToken } from "@/utils/auth";

export default {
  name: "MyFiles",
  components: {
    FileUpload
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
      // 云盘文件表格数据
      filesList: [],
      paths: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型字典
      typeOptions: [],
      // 类型字典
      yesnoOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        folderId: 0,
        fileId: null,
        type: null,
        title:null,
        isPublic:null
      },
      // 上传参数
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
        url: process.env.VUE_APP_BASE_API + "/sys/files/upload"
      },
      //当前目录
      folderId: 0,
      typeIndex: "11",
      // 表单参数
      form: {},
      // 是否显示弹出层
      folderOpen: false,
      folderForm: {},
      shareForm: {},
      // 表单校验
      rules: {
        folderId: [
          { required: true, message: "目录不能为空", trigger: "blur" }
        ],
        fileId: [
          { required: true, message: "文件不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ]
      },
      // 表单校验
      folderRules: {
        title: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
        pid: [
          { required: true, message: "上级不能为空", trigger: "blur" }
        ],
        isPublic: [
          { required: true, message: "是否公开不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("file_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.yesnoOptions = response.data;
    });
  },
  methods: {
    /** 查询云盘文件列表 */
    getList() {
      if(this.typeIndex == '11'){//主页
        this.queryParams.folderId = this.folderId;
        this.queryParams.type = null;
        this.getFolderAndFiles();
      }else if(this.typeIndex == '12'){//共享
        this.loading = true;
        var list = [];
        listShareFiles(this.queryParams).then(response => {
          for(var i=0;i<response.rows.length;i++){
            list.push({
              id:'file_'+response.rows[i].id,
              title:response.rows[i].title,
              type:response.rows[i].type,
              isPublic:response.rows[i].isPublic,
              uploadTime:response.rows[i].uploadTime
            });
          }
          this.filesList = list;
          this.loading = false;
        });
      }else if(this.typeIndex == '0'){//全部文件
        this.queryParams.folderId = null;
        this.queryParams.type = null;
        this.getFiles();
      }else{//按类型检索
        this.queryParams.folderId = null;
        this.queryParams.type = this.typeIndex;
        this.getFiles();
      }
    },
    getFolderAndFiles(){
      this.loading = true;
      var list = [];
      listMyFolder({pid:this.folderId}).then(response => {
        for(var i=0;i<response.rows.length;i++){
          list.push({
            id:'folder_'+response.rows[i].id,
            title:response.rows[i].title,
            isPublic:response.rows[i].isPublic,
            type:'folder'
          });
        }
        this.queryParams.folderId = this.folderId;
        listMyFiles(this.queryParams).then(response => {
          for(var i=0;i<response.rows.length;i++){
            list.push({
              id:'file_'+response.rows[i].id,
              title:response.rows[i].title,
              type:response.rows[i].type,
              isPublic:response.rows[i].isPublic,
              uploadTime:response.rows[i].createTime
            });
          }
          this.filesList = list;
          this.loading = false;
        });
      });
    },
    getFiles(){
      this.loading = true;
      var list = [];
      listMyFiles(this.queryParams).then(response => {
        for(var i=0;i<response.rows.length;i++){
          list.push({
            id:'file_'+response.rows[i].id,
            title:response.rows[i].title,
            type:response.rows[i].type,
            isPublic:response.rows[i].isPublic,
            uploadTime:response.rows[i].createTime
          });
        }
        this.filesList = list;
        this.loading = false;
      });
    },
    // 类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
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
        folderId: 0,
        fileId: null,
        title: null,
        type: null
      };
      this.resetForm("form");
    },
    // 节点单击事件
    handleType(index) {
      if(index == '12'){
        this.$router.replace('/person/yun/files/share');
        return;
      }else if(index > '0'){
        this.folderId = 0;
      } else{
        this.folderId = null;
      }

      this.typeIndex = index;
      this.paths = [];
      this.getList();
    },
    clickFolder(folder){
      this.resetForm("queryForm");
      this.folderId = folder.id.substr(7);
      this.getList();
      this.paths.push({
        title: folder.title,
        id: this.folderId
      });
      this.typeIndex = "11";
    },
    handleUpFolder(){
      if(this.paths.length == 0) return;

      this.paths.pop();
      if(this.paths.length == 0){
        this.folderId = 0;
      }else{
        this.folderId = this.paths[this.paths.length-1].id;
      }
      this.typeIndex = "11";
      this.getList();
    },
    clickFile(row){
      var id = row.id;
      if(row.id.substr(0,5) == 'file_'){
        id = row.id.substr(5);
      }
      window.location.href = process.env.VUE_APP_BASE_API + "/yun/files/download/" + id;
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
      this.title = "上传文件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if(row.id.substr(0,5) == 'file_'){
        this.$prompt('请输入目录名称', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: row.title
        }).then(({ value }) => {
          var fileForm = {
            id: row.id.substr(5),
            title: value
          };
          updateFiles(fileForm).then(response => {
            this.msgSuccess("修改文件名成功");
            this.getList();
          });
        });
      }else{
        this.$prompt('请输入目录名称', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: row.title
        }).then(({ value }) => {
          var folderForm = {
            id: row.id.substr(7),
            title: value,
            pid: this.folderId
          };
          updateFolder(folderForm).then(response => {
            this.msgSuccess("修改目录名成功");
            this.getList();
          });
        });
      }
    },
    /** 新增按钮操作 */
    handleAddFolder() {
      this.$prompt('请输入目录名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        var folderForm = {
          title: value,
          pid: this.folderId
        }
        addFolder(folderForm).then(response => {
          this.msgSuccess("创建目录成功");
          this.getList();
        });
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFiles(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFiles(this.form).then(response => {
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
      this.$confirm('是否确认删除选中的文件和目录吗，目录删除后，文件将转移到根目录?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFiles(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    //共享/取消
    handleShare(row) {
      const ids = row.id || this.ids;
      if(row.id){
        if(row.isPublic == 'Y'){
          this.$confirm('取消共享后，其他人将无法找到该文件，您确认要继续操作吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return shareFiles(row.id);
            }).then(() => {
              this.getList();
              this.msgSuccess("取消共享成功");
            })
        }else{
            this.$confirm('共享成功后，其他人都可以看到该文件，您确认要继续操作吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return shareFiles(row.id);
            }).then(() => {
              this.getList();
              this.msgSuccess("共享成功");
            })
        }
      }else{
        this.$confirm('共享成功后，其他人都可以看到这些文件，您确认要继续操作吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return shareFiles(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("共享成功");
        })
      }
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

      this.form.fileId = response.data.id;
      this.form.title = response.data.title;
      this.form.folderId= this.folderId;
      addFiles(this.form).then(response => {
        this.msgSuccess("上传文件成功");
        this.open = false;
        this.getList();
      });
    }
  }
};
</script>
