function getWord(x,sequence, w1, w2)    //创建函数
{
    var s = sequence[x-1]
    var res;
    if (s == 0) {
        res = w1
    } else if (s == 1) {
        res = w2
    } else if (s == 2) {
        res = "白板"
    } else if (s == 3) {
        res = "观众"
    }
    return res
}

//给定一个值,使点确定为true,点其他为false
var isDelete = false;

function showPopup(index, word) {
    let msg = document.getElementById("msg_notify");
    let btn1 = document.getElementById("btn1");
    btn1.value = word;
    msg.innerHTML = "确认打开" + index + "号？"
    document.getElementById("popup").style.display = "flex";

}

// 传一个参数，把true和false传进去
function hidePopup(x) {
    //处理冒泡
    //event 的 cancelable 事件返回一个布尔值 。
    // 如果返回的是undefined，则是除确定以外的区域，反之是确定按钮（因为取消和其他区域，没传值默认undefined）
    if (x) {
        let word = document.getElementById("btn1").value;
        alert(word);    //弹出窗口
    }
    document.getElementById("popup").style.display = "none";
    isDelete = x;
    console.log(x);
}