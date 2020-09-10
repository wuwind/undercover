<%--
  Created by IntelliJ IDEA.
  User: wuhf
  Date: 2020/9/7
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.wuwind.controller.bean.ArticleItemBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ArrayList<ArticleItemBean> datas = new ArrayList<>();
    int textCount = 0;
    int imgCount = 0;
%>
<%
    //    ArticleItemBean bean = new ArticleItemBean();
//    datas.add(bean);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="altContent" action="<%=request.getContextPath()%>/api/article_edit" method="post"
      enctype="multipart/form-data">
    <input name="title" value="title"/><br>
    <textarea name="item_text" rows="6" cols="50%"></textarea><br>
    <input type="file" id="input" name="file" onchange="handleFiles(this.files)"><br>
    <div id="preview"></div>

    <button type="submit" name="submit" value="submit">submit</button>
</form>
<button type="button" onclick="addText();">添加文本</button>
<br>
<button type="button" onclick="handleFiles();">添加图片</button>
<br>
</body>

<script>

    function addText() {
<%--        <%--%>
<%--        textCount++;--%>
<%--        ArticleItemBean  bean =  new ArticleItemBean();--%>
<%--        bean.setType(1);--%>
<%--        bean.setValue(textCount+" index");--%>
<%--        datas.add(bean);--%>
<%--        %>--%>
<%--        window.location.reload();--%>
        const preview = document.getElementById('preview');
        const node = document.createElement("input");
        preview.appendChild(node);
    }

    function addImg() {
<%--        <%--%>
<%--        textCount++;--%>
<%--        ArticleItemBean  bean2 =  new ArticleItemBean();--%>
<%--        bean.setType(2);--%>
<%--        bean.setValue(textCount+" index");--%>
<%--        datas.add(bean2);--%>
<%--        %>--%>
<%--        window.location.reload();--%>
        const preview = document.getElementById('preview');
        const node = document.createElement("input");
        preview.appendChild(node);

    }

    $('body').on('change','#upLoad',function(){
        var formData = new FormData();
        var name = $($(this)).val();
        var files = $($(this))[0].files[0];
        formData.append("file", files);
        formData.append("name", name);
        //另外加的参数
        formData.append("act", "PostImg");
        var index = layer.msg('正在提交中....');
        $.ajax({
            url: '/api/article_edit',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success:function (res) {
                alert('上传成功');
            }
            ,error:function (res) {
                alert('错误');
            }
        });
    })

    function handleFiles(files) {
        var preview = document.getElementById('preview');
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /^image\//;

            if (!imageType.test(file.type)) {
                continue;
            }

            var img = document.createElement("img");
            img.classList.add("obj");
            img.file = file;
            // 假设 "preview" 是将要展示图片的 div
            preview.appendChild(img);

            var reader = new FileReader();
            reader.onload = (function (aImg) {
                return function (e) {
                    aImg.src = e.target.result;
                };
            })(img);

            reader.readAsDataURL(file);
        }
    }
</script>

</html>
