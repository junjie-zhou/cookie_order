<html>
<head>
    <meta charset="utf-8">
    <title>成功提示</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg!""}</strong><a href="${url}" class="alert-link"><span>3</span>s后自动跳转</a>
            </div>
        </div>
    </div>
</div>
<#--<div id="count">
    <div id="sec-count" class="numbers"></div>
</div>-->
</body>

<script>
    setTimeout('location.href="${url}"', 3000);
</script>
<#--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="/sell/js/jcountdown.min.js"></script>-->
</html>