<template>
  <div class="edit_container">
    <quill-editor
      v-model="content"
      class="myQuillEditor"
      ref="myQuillEditor"
      :options="editorOption"
      @blur="onEditorBlur($event)"
      @focus="onEditorFocus($event)"
      @change="onEditorChange($event)"
    ></quill-editor>
    <!-- 文件上传 -->
    <el-upload
      ref="upload"
      :data="upDate"
      :action="actionStr"
      class="avatar-uploader"
      multiple
      :show-file-list="true"
      :on-success="handleSuccess"
      :disabled="isdisable"
      :before-upload="handleBeforeUpload"
      :auto-upload="false"
      :headers="headerObj"
      type="drag"
      style="display: inline-block"
    ></el-upload>

    <el-button
      size="small"
      type="primary"
      class="my-upload"
      v-show="false"
      style="height:0"
    >点击上传
    </el-button>
    <div slot="tip" class="el-upload__tip" style="margin-top:0">已选择附件：</div>
  </div>
</template>

<script>

import {quillEditor} from "vue-quill-editor";
import "quill/dist/quill.core.css";
import "./quill.snow.css"
//import 'quill/dist/quill.snow.css'
import "quill/dist/quill.bubble.css";
import * as Quill from 'quill'
// import * as Quill from '../utils/quill';

import imageResize from 'quill-image-resize-module'
import {ImageDrop} from 'quill-image-drop-module'
import process from "shelljs";

Quill.register('modules/imageDrop', ImageDrop)
Quill.register('modules/imageResize', imageResize)

//设置字体大小
let fontSizeStyle = Quill.import('attributors/style/size') //引入这个后会把样式写在style上
fontSizeStyle.whitelist = ['12px', '14px', '16px', '18px', '20px', '22px', '24px', '28px', '32px', '36px'];
Quill.register(fontSizeStyle, true);

//设置光标位置
const bindings = {
  custom: {
    key: 13,
    handler: function (range, context) {
      this.quill.insertText(range.index, "\n ");
      setTimeout(() => {
        let nowRange = this.quill.getSelection().index - 1;
        this.quill.setSelection(nowRange);
      })
    }
  }
}
export default {
  name: "editorVue",
  components: {quillEditor},
  props: ['contentText'],
  data() {

    // 工具栏配置
    const toolbarOptions = [
      ["bold", "italic", "underline", "strike"], //加粗，斜体，下划线，删除线
      ["blockquote", "code-block"], //引用，代码块
      [{header: 1}, {header: 2}], // 标题，键值对的形式；1、2表示字体大小
      [{list: "ordered"}, {list: "bullet"}], //列表
      [{script: "sub"}, {script: "super"}], // 上下标
      [{indent: "-1"}, {indent: "+1"}], // 缩进
      [{direction: "rtl"}], // 文本方向
      [{size: ['12px', '14px', '16px', '18px', '20px', '22px', '24px', '28px', '32px', '36px']}], // 字体大小
      [{header: [1, 2, 3, 4, 5, 6, false]}], //几级标题
      [{color: []}, {background: []}], // 字体颜色，字体背景颜色
      [{font: []}], //字体
      [{align: []}], //对齐方式
      ["clean"], //清除字体样式
      ['image', 'upload'], // 链接、图片、视频
      // ['sourceEditor']
    ];

    return {
      content: '', // 富文本编辑器默认内容
      isdisable: true,
      studentNum: '',
      actionStr: '',
      headerObj: {
        token: localStorage.getItem("token"),
      },
      upDate: {},
      // 富文本编辑器配置
      editorOption: {
        modules: {
          toolbar: {
            container: toolbarOptions,
            handlers: {
              'upload': function () { // 添加工具方法
                document.querySelector('.avatar-uploader input').click()
              }
            }
          }, // 自定义工具栏，与上面定义的toolbarOptions 相对应
          // 新增下面
          imageDrop: true, // 拖动加载图片组件。
          imageResize: { //调整大小组件。
            displayStyles: {
              backgroundColor: 'black',
              border: 'none',
              color: 'white'
            },
            modules: ['Resize', 'DisplaySize', 'Toolbar']
          },
          keyboard: {
            bindings
          }
        },
        theme: "snow", //主题
        placeholder: "请输入正文",
      },
      headers: {
        Token: "",
      },
      uploadUrl: process.env.VUE_APP_URL,
      fileList: [],
    };
  },
  watch: {
    contentText(newval) {
      this.content = newval;
    }
  },
  methods: {
    sendHomework() {

      this.$refs.upload.submit();
    },
    sendTest() {

      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    // 失去焦点事件
    onEditorBlur(editor) {
      // console.log("editor blur!", editor);
    },
    // 获得焦点事件
    onEditorFocus(editor) {
      // console.log("editor focus!", editor);
    },
    // 内容改变事件
    onEditorChange({quill, html, text}) {

      this.$emit('editorContext', this.content)
    },
    //添加中文注释
    addAnnotation() {
      // toolbar标题
      const titleConfig = [
        {Choice: '.ql-insertMetric', title: '跳转配置'},
        {Choice: '.ql-bold', title: '加粗'},
        {Choice: '.ql-italic', title: '斜体'},
        {Choice: '.ql-underline', title: '下划线'},
        {Choice: '.ql-header', title: '段落格式'},
        {Choice: '.ql-strike', title: '删除线'},
        {Choice: '.ql-blockquote', title: '块引用'},
        {Choice: '.ql-code', title: '插入代码'},
        {Choice: '.ql-code-block', title: '插入代码段'},
        {Choice: '.ql-font', title: '字体'},
        {Choice: '.ql-size', title: '字体大小'},
        {Choice: '.ql-list[value="ordered"]', title: '编号列表'},
        {Choice: '.ql-list[value="bullet"]', title: '项目列表'},
        {Choice: '.ql-direction', title: '文本方向'},
        {Choice: '.ql-header[value="1"]', title: 'h1'},
        {Choice: '.ql-header[value="2"]', title: 'h2'},
        {Choice: '.ql-align', title: '对齐方式'},
        {Choice: '.ql-color', title: '字体颜色'},
        {Choice: '.ql-background', title: '背景颜色'},
        {Choice: '.ql-image', title: '图像'},
        {Choice: '.ql-video', title: '视频'},
        {Choice: '.ql-link', title: '添加链接'},
        {Choice: '.ql-formula', title: '插入公式'},
        {Choice: '.ql-clean', title: '清除字体格式'},
        {Choice: '.ql-script[value="sub"]', title: '下标'},
        {Choice: '.ql-script[value="super"]', title: '上标'},
        {Choice: '.ql-indent[value="-1"]', title: '向左缩进'},
        {Choice: '.ql-indent[value="+1"]', title: '向右缩进'},
        {Choice: '.ql-header .ql-picker-label', title: '标题大小'},
        {Choice: '.ql-header .ql-picker-item[data-value="1"]', title: '标题一'},
        {Choice: '.ql-header .ql-picker-item[data-value="2"]', title: '标题二'},
        {Choice: '.ql-header .ql-picker-item[data-value="3"]', title: '标题三'},
        {Choice: '.ql-header .ql-picker-item[data-value="4"]', title: '标题四'},
        {Choice: '.ql-header .ql-picker-item[data-value="5"]', title: '标题五'},
        {Choice: '.ql-header .ql-picker-item[data-value="6"]', title: '标题六'},
        {Choice: '.ql-header .ql-picker-item:last-child', title: '标准'},
        {Choice: '.ql-size .ql-picker-item[data-value="small"]', title: '小号'},
        {Choice: '.ql-size .ql-picker-item[data-value="large"]', title: '大号'},
        {Choice: '.ql-size .ql-picker-item[data-value="huge"]', title: '超大号'},
        {Choice: '.ql-size .ql-picker-item:nth-child(2)', title: '标准'},
        {Choice: '.ql-align .ql-picker-item:first-child', title: '居左对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="center"]', title: '居中对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="right"]', title: '居右对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="justify"]', title: '两端对齐'},
        {Choice: '.ql-upload', title: '上传文件'},
      ];
      for (let item of titleConfig) {
        let tip = document.querySelector('.quill-editor ' + item.Choice);
        if (!tip) continue
        tip.setAttribute('title', item.title)
      }
    },
    //上传连接
    async onUploadHandler(e) {
      const imageUrl = e.target.value;

      // 获取光标所在位置
      let quill = this.$refs.myQuillEditor.quill
      let length = quill.getSelection().index
      // 插入图片
      quill.insertEmbed(length, 'image', imageUrl)
      // 调整光标到最后
      quill.setSelection(length + 1)
      // this.content += url
    },
    //文件上传成功
    handleSuccess(res, file) {
      if (res.flag === 'right') {
        this.$message.success("提交成功!");
      } else {
        this.$message.error("提交失败!");
      }
      let fileNameLength = file.name.length;
      // 获取光标所在位置
      let quill = this.$refs.myQuillEditor.quill;
      let length = quill.getSelection().index;
      // 插入连接 res为连接地址
      quill.insertEmbed(length, 'link', {href: res, innerText: file.name}, "api")
      // 调整光标到最后
      quill.setSelection(length + fileNameLength);
    },
    handleBeforeUpload() {

    },
    // 自定义按钮内容初始化
    initButton() {
      const editorButton = document.querySelector('.ql-upload');
      editorButton.innerHTML = '<i class="el-icon-upload" style="font-size: 18px;color:black"></i>'
    },


  },
  computed: {
    editor() {
      return this.$refs.myQuillEditor.quill;
    },
  },
  mounted() {
    this.studentNum = localStorage.getItem('userId');
    let token = localStorage.getItem("token");
    if (token) {
      this.headers = {
        Token: token,
      };
    }
    this.$myBus.$on('sendHomework', () => {
      this.upDate = {
        studentNum: this.studentNum,
        homeworkNum: this.$store.state.student.homeworkNum,
        submitContext: this.content
      }
      this.actionStr = 'http://localhost:8090/sendHomework';
      setTimeout(this.sendHomework, 1)
    })

    this.$myBus.$on('sendTest', () => {
      this.upDate = {
        studentNum: this.studentNum,
        testNum: this.$store.state.student.testNum,
        submitContext: this.content
      }
      this.actionStr = 'http://localhost:8090/sendTest'
      setTimeout(this.sendTest, 1);
    })

    this.$nextTick(() => {
      this.initButton();
      this.addAnnotation();
    })
  },
  beforeDestroy() {
    this.$myBus.$off('sendHomework');
    this.$myBus.$off('sendTest');
  }
};
</script>

<style>
.ql-snow .ql-tooltip[data-mode="link"]::before {
  content: "请输入链接地址:";
}

.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "保存";
  padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "请输入视频地址:";
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: "14px"; /*no*/
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: "文本";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: "标准字体";
}

.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "衬线字体";
}

.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "等宽字体";
}

.ql-editor {
  min-height: 150px;
}

.ql-snow.ql-toolbar .ql-upload {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

