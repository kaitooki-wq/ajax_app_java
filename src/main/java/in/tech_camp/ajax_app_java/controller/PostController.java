package in.tech_camp.ajax_app_java.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.tech_camp.ajax_app_java.entity.PostEntity;
import in.tech_camp.ajax_app_java.form.PostForm;
import in.tech_camp.ajax_app_java.repository.PostRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {

  private final PostRepository postRepository;

  @GetMapping("/")
  public String showList(Model model) {
    var postList = postRepository.findAll();
    model.addAttribute("postList", postList);
    model.addAttribute("postForm", new PostForm());
    return "posts/index";
  }


  @PostMapping("/posts")
  // JavaScriptの XHR.open("POST", "/posts") から送られてきた電波をキャッチする窓口（URL）です。
  public ResponseEntity<PostEntity> savePost(@ModelAttribute("postForm") PostForm form){
    // 送信したフォームの入力内容を、Javaの form という名前の箱（オブジェクト）に自動で詰め替えて受け取っています。 
    // ResponseEntity<PostEntity>このメソッドの最終目的は、処理が終わった後に「データ（PostEntity）を乗せたレスポンス（返事）」をブラウザに送り返すことだよ、と宣言しています。
    System.out.println("メソッド呼び出し：" + form);

    PostEntity post = new PostEntity();
    // データベース（DB）に保存するためには、DB専用の形式である PostEntity 型の新しい箱（インスタンス） が必要なので、ここで post という名前で新しく生み出しています。
    post.setContent(form.getContent());
    // 画面から届いた荷物（form.getContent() で中身の文字列を取り出す）を、いま作ったDB用の箱の中身（post.setContent(...)）へと詰め替えています。
    postRepository.insert(post);
    // データの詰まった箱（post）を、データベースを操作する専門家（postRepository）に渡して、実際にデータベースに保存（SQLのINSERT文を実行）しています。この瞬間に、データが安全に保管されます。
    PostEntity resultPost = postRepository.findById(post.getId());
    // 保存した直後に、そのデータのIDを使って「今まさにDBに保存された、ID付きの完璧な最新データ」をDBから1件引っ張り出してきて、resultPost という変数に代入しています。
    // （※なぜわざわざもう一度取るかというと、DBに保存された時に自動で割り振られる「投稿日時」や「自動連番のID」などが反映された、完全体のデータを画面に返してあげたいからです）
    System.out.println(resultPost);
    return ResponseEntity.ok(resultPost);
    // ResponseEntity.ok(...)：「通信成功！（ステータスコード200 OK）」という安心のサインと一緒に、先ほどDBから取ってきた最新データ（resultPost）をセットにしています。
    // これを return（返却）することで、パシリ（XHR）にお土産を持たせて、JavaScript（画面）へ向かって送り返します。
  }
  
} 
 
 
 
 