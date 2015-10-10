function addProduct(productId){document.cart.productId.value=productId;var cart=CART+"?actionType=addproduct&productId="+productId;window.location.href=cart;}
function addPorductRequest(){if(http_request.readyState==4){if(http_request.status==200){var returnText=http_request.responseText;if(returnText=="noLoginUser"){alert("你还没有登陆,只有登陆用户才能购买商品");return;}
if(returnText=="noProduct"){alert("你购买的商品暂时没货,请与商城联系");return;}
if(returnText=="addProductSuccess"){alert("商品已经添加到你的购物车了,你可以查看你的购物车或结帐!");}}else{}}}