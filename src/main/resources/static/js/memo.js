function post (){
 const submit = document.getElementById("submit");
  //  document.getElementById("submit"); ここでsubmit id の要素を取得して左辺のsubmitに代入
   submit.addEventListener("click", (e) => {
    e.preventDefault();
 submit.addEventListener("click", () => {
  // clickというイベントが起きた時に実行する関数を定義　（addEventListenerはイベントが起きたときに実行する関数を定義するメソッド）
  const form = document.getElementById("form");
  // 取得したformの要素を左辺のform変数に代入
  const formData = new FormData(form);
  // ここでformに入力された要素を取得
   const XHR = new XMLHttpRequest();
  //  非同期通信のためのオブジェクトを生成
   XHR.open("POST", "/post" , true);
  //  リクエストを初期化（新しく使うものに対して最初に必要な準備や設定をすること）
   XHR.responseType="json";
  //  サーバーからのレスポンスデータの形式を指定
  XHR.send(formData);
    });
});

window.addEventListener('load', post)}; 
