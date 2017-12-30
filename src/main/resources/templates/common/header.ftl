<head>
    <meta charset="utf-8">
    <title>卖家后端管理系统</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/static/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/sell/static/favicon.ico">
    <!-- 引入jQuery异步上传js文件 -->
    <script src="/sell//static/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/sell//static/js/jquery.formEdit.js" type="text/javascript"></script>
    <script src="/sell//static/js/jquery.form.js" type="text/javascript"></script>
    <!-- Ajax异步上传图片 -->
    <script type="text/javascript">
        function uploadPic() {
            // 上传设置
            var options = {
                // 规定把请求发送到那个URL
                url: "/sell/seller/product/uploadPic",
                // 请求方式
                type: "post",
                // 服务器响应的数据类型
                dataType: "json",
                // 请求成功时执行的回调函数
                success: function(data, status, xhr) {
                    // 图片显示地址
                    $("#allUrl").attr("src", data.url);
                    $("#iconUrl").attr("value", data.url);
                }
            };

            $("#jvForm").ajaxSubmit(options);
        }
    </script>
</head>