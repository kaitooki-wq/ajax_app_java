const buildHTML = (XHR) => {
  const item = XHR.response;
  const html = `
    <div class="post">
      <div class="post-date">
        投稿日時：${item.createdAt}
      </div>
      <div class="post-content">
        ${item.content}
      </div>
    </div>`;
  return html;
};

function post (){
 const submit = document.getElementById("submit");
  //  document.getElementById("submit"); ここでsubmit id の要素を取得して左辺のsubmitに代入
   submit.addEventListener("click", (e) => {
    e.preventDefault();

  // clickというイベントが起きた時に実行する関数を定義　（addEventListenerはイベントが起きたときに実行する関数を定義するメソッド）
  const form = document.getElementById("form");
  // 取得したformの要素を左辺のform変数に代入
  const formData = new FormData(form);
  // ここでformに入力された要素を取得
   const XHR = new XMLHttpRequest();
  //  非同期通信のためのオブジェクトを生成
  XHR.open("POST", "/posts", true);
  //  リクエストを初期化（新しく使うものに対して最初に必要な準備や設定をすること）
   XHR.responseType="json";
  //  サーバーからのレスポンスデータの形式を指定
  XHR.onload = () => {
    if (XHR.status != 200) {
        alert(`Error ${XHR.status}: ${XHR.response.error}`);
        return null;
      };
    const list = document.getElementById("list");
    const formText = document.getElementById("content");
    list.insertAdjacentHTML("afterend", buildHTML(XHR));
    const item = XHR.response;
      formText.value = "";
    };
   XHR.send(formData);
    });
};

window.addEventListener('load', post); 
