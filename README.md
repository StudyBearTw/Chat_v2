# chat2_v2

## 專案簡介
chat2_v2 是一個以 Java Swing 製作的簡易聊天室程式，包含 Client 與 Server 兩端，支援文字訊息即時傳送。

## 專案結構
src/ ├── ClientApp.java ├── ClientGui.java ├── Client.java ├── EchoServer.java ├── ServerApp.java ├── ServerGui.java ├── Util.java


## 執行方式

### Server 端
1. 編譯所有 Java 檔案：
    ```sh
    javac -d bin src/*.java
    ```
2. 啟動 Server：
    ```sh
    java -cp bin ServerApp
    ```

### Client 端
1. 編譯所有 Java 檔案：
    ```sh
    javac -d bin src/*.java
    ```
2. 啟動 Client：
    ```sh
    java -cp bin ClientApp
    ```

## 功能說明
- 雙方可即時傳送文字訊息。
- 支援按 Enter 或點擊 "Sent" 傳送訊息。
- "Close" 按鈕可關閉程式。

## 主要技術
- Java Swing：建立視窗介面。
- Java Socket：實現網路通訊。
- 多執行緒：Server 端同時處理多個 Client。
- 事件監聽：GUI 元件偵測使用者操作。

## 注意事項
- Server 必須先啟動並監聽，Client 才能連線。
- 預設編碼為 UTF-16，請勿傳送特殊字元避免亂碼。

## 聯絡作者
如有問題請聯絡 [你的名字或email]。