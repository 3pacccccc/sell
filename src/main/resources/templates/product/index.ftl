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
                    <form role="form" method="post" action="/sell/seller/product/save">

                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control"
                                   value="${(productInfo.productName)!""}"/>
                        </div>

                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control"
                                   value="${(productInfo.productPrice)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control"
                                   value="${(productInfo.productStock)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control"
                                   value="${(productInfo.productDescription)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <#if productInfo?? && productInfo.productIcon??><img src="${(productInfo.productIcon)!""}" alt="" height="100" width="100"></#if>
                            <input name="productIcon" type="text" class="form-control"
                                   value="${(productInfo.productIcon)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list productCategoryList as category>
                                <#--??表示存在-->
                                    <option value="${category.categoryId}" <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType> selected</#if>>
                                    ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <#--<input hidden type="text" name="productId" value="${productInfo.productId!''}">-->
                        <input hidden type="text" name="productId" value="<#if productInfo?? && productInfo.productId??>${productInfo.productId}<#else></#if>">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>