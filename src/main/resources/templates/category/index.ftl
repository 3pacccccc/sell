<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--侧边栏-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">

                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control"
                                   value="${(productCategory.categoryName)!""}"/>
                        </div>

                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" type="number" class="form-control"
                                   value="${(productCategory.categoryType)!""}"/>
                        </div>
                        <input hidden type="number" name="categoryId" value="<#if productCategory?? && productCategory.categoryId??>${productCategory.categoryId}<#else></#if>">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>