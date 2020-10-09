document.querySelector('.img__btn')
    .addEventListener('click', function() {
    document.querySelector('.content').classList.toggle('s--signup')
})
// $(function(){
//     var button = $("#getcode");
//     var number = 60;
//     var countdown = function(){
//         alert(12);
//         if (number == 0) {
//             button.attr("disabled",false);
//             button.html("获取验证码");
//             number = 60;
//             return;
//         } else {
//             button.attr("disabled",true);
//             button.html(number + "秒 重新发送");
//             number--;
//         }
//         setTimeout(countdown,1000);
//     }
//     setTimeout(countdown,1000);
// })
var countdownHandler = function(){
    var $button = $(".getcode");
    var number = 60;
    var countdown = function(){
        if (number == 0) {
            $button.attr("disabled",false);
            $button.html("发送验证码");
            number = 60;
            return;
        } else {
            $button.attr("disabled",true);
            $button.html(number + "秒 重新发送");
            number--;
        }
        setTimeout(countdown,1000);
    }
    setTimeout(countdown,1000);
 }
// $("#getcode1").click(function () {
// countdownHandler();
// });
$("#get_code").click(function () {
    // console(12);

    let email= $("#r_email").val();
    if(email==""){
        alert("请输入邮箱");
    }
    else {
        countdownHandler();
        $.ajax({
            type: "GET",
            url: "anime/code",
            data: {
                "email": $("#r_email").val()
            },
            dataType:"json",
            success: function (data) {
                alert(data.msg);
               // if(data.msg=="error"){
               //     alert("邮箱不存在")
               // }
            }
        })
    }
});
// $("#password").blur(function () {
//     let decu=this.value;
//     let rule=new RegExp("[\\dA-Za-z]{6,16}");
//     if(decu!="") {
//         if (!rule.test(decu)) {
//             this.style.cssText = "color:red";
//             document.getElementById("div2").style.cssText = "color:red";
//             $("#div2").text("输入格式错误，6-16位的字符");
//         } else {
//             this.style.cssText = "color:green";
//             document.getElementById("div2").style.cssText = "color:green";
//             $("#div2").text("格式正确");
//         }
//     }
//     else {
//         $("#div2").text("");
//     }
// })
var passwordRule=function (rule,object) {
// var l="[\\dA-Za-z]{6,16}";
    let rules=new RegExp(rule);
    let decu=object.value;
    if(decu!="") {
        if (!rules.test(decu)) {
            // alert(rule)
            object.style.cssText = "color:red";
            return "false"
        } else {
            object.style.cssText = "color:green";
            return "ture"
        }
    }
    return "false";
    }
    // passwordRule("123");
$("#r_password").blur(function () {
var rule="[\\dA-Za-z]{6,16}";
passwordRule(rule,this);
})
$("#r_password_2").blur(function () {
    var rule="[\\dA-Za-z]{6,16}";
    passwordRule(rule,this);
})
var judgepass=function(){// 判断密码是否一致
    let pass1=$("#r_password_2").val();
    let pass2=$("#r_password").val();
    let msg="";
    // alert(pass2);
    // alert(pass1);
    if(pass1!=pass2){
        msg="两次密码不一致，请重新输入";
        return msg;
    }
    return msg;
}
$("#r_email").blur(function () {
var rule="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$";// 邮箱的验证规则
    passwordRule(rule,this);
})

$("#register").click(function () {//注册的点击事件
    let flag= Boolean();
    flag=true;
    // alert(12);
   let p=document.getElementById("r_password");
   let e=document.getElementById("r_email");
   let decum=document.getElementById("r_username").value;
   let code=document.getElementById("input_code").value;
   // alert(decum);
    var rule="[\\dA-Za-z]{6,16}";
 let pass= passwordRule(rule,p);
  let msg=judgepass(); //验证两次密码是否一致
    var rule2="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$";// 邮箱的验证规则
    let email = passwordRule(rule2, e);
    if(decum==""){
        alert("用户名不能为空")
        flag=false;
    }else if(pass=="false"){
        alert("密码格式不正确，请重新输入6-16位字符")
        flag=false;
    }else if(msg!=""){
        alert(msg);
        flag=false;
    }else if(email=="false"){
        alert("邮箱格式不正确");
        flag=false;
    }else if(code==""){
        alert("验证码不能为空")
        flag=false;
    }


    // $.ajax({
    //     type: "GET",
    //     url: "anime/code",
    //     data: {
    //         "code": $("#r_email").val()
    //     },
    //     dataType:"json",
    //     success: function (data) {
    //         if(data.msg=="error"){
    //             alert("邮箱不存在")
    //         }
    //     }
    // })
    if(flag){
        $.ajax({
            url:"anime/register",
            type:"post",
            data:{
                "username": $("#r_username").val(),
                "password": $("#r_password").val(),
                "email": $("#r_email").val(),
                "code":$("#input_code").val()
            },
            dataType: "json",
            success:function (data) {
                alert(data.msg);
                if(data.flag=="true"){
                    if($("#r_email").val()!=""){
                       let email= $("#email");
                      email.val($("#r_email").val());
                    }
                document.querySelector('.content').classList.toggle('s--signup');
                }
                // if(data.msg=="false"){
                //     alert("邮箱已存在，请重新注册");
                // }
                // else alert("欢迎你的加入！");
            }
            }
        )
    }

})
// 登录页面

$("#email").blur(function () { // 邮箱
    var rule="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$";// 邮箱的验证规则
    passwordRule(rule,this);
})
$("#password").blur(function () { // 密码
    var rule="[\\dA-Za-z]{6,16}";
    passwordRule(rule,this);
})
$("#login").click(function () {
    let flag= Boolean();
    flag=true;
    // alert(12);
    let p=document.getElementById("password");
    let e=document.getElementById("email");
    var rule="[\\dA-Za-z]{6,16}";
    let pass= passwordRule(rule,p);
    var rule2="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$";// 邮箱的验证规则
    let email = passwordRule(rule2, e);
  if(email=="false"){
        alert("邮箱格式不正确");
        flag=false;
    }else if(pass=="false"){
        alert("密码格式不正确，请重新输入6-16位字符")
        flag=false;
    }
  if(flag){
      $.ajax({
          url:"anime/login",
          data:{
              "email": $("#email").val(),
              "password":$("#password").val()
          },
          dataType:"json",
          type:"post",
          success:function (data) {
              if(data.msg=="true"){
                  window.location.href="../index.jsp"
              }
              else
                  alert(data.msg);
          }
      })
  }
})
