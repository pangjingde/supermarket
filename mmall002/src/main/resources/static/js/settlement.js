$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj) {
    let index = $(".car_btn_2").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    let stock = parseInt($(".productStock").eq(index).val());;
    if (quantity == stock) {
        alert("库存不足！");
        return false;
    }
    quantity++;
    let id=parseInt($(".id").eq(index).val());
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price;
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        success:function (data){
            if(data=="success"){
                $(".qprice").eq(index).text('￥'+cost);
                $(".car_ipt").eq(index).val(quantity);
                var array = $(".qprice");
                var totalCost = 0;
                for(var i = 0;i < array.length;i++){
                    var val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥"+totalCost);
            }

        },
        error: function (){

        }
    });
}



//商品数量--
function subQuantity(obj){
    let index = $(".car_btn_1").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    if(quantity == 1){
        alert("至少选择一件商品！");
        return false;
    }
    quantity--;
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price;
    let id=parseInt($(".id").eq(index).val());
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        success:function (data){
            if(data=="success"){
                $(".qprice").eq(index).text('￥'+cost);
                $(".car_ipt").eq(index).val(quantity);
                var array = $(".qprice");
                var totalCost = 0;
                for(var i = 0;i < array.length;i++){
                    var val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥"+totalCost);
            }

        },
        error: function (){

        }
    });
}

//移出购物车
function removeCart(obj){
    let index = $(".delete").index(obj);
    let id = parseInt($(".id").eq(index).val());
    if(confirm("是否确定删除?")){
        window.location.href = "/cart/deleteById/"+id;
    }
}

function settlement2(){
      var totalCost=$("#totalprice").text();
        if (totalCost == "￥0"){
            alert("购物车为空，不能进行结算")
            return false;
        }else {
         window.location.href="/cart/settlement2";
        }

}
