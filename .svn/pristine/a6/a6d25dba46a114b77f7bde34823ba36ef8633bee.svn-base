package com.py.shijijingying.vo;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.py.shijijingying.service.UserService;

@ServerEndpoint("/websocket/{id}")
public class WebSocketController {
	/**
	 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
	 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
	 */
	    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	    private static int onlineCount = 0;
	    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

	    //与某个客户端的连接会话，需要通过它来给客户端发送数据
	    private Session session;
	    
	    //接收id
	    private String id = "";

	    /**
	     * 连接建立成功调用的方法
	     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	     * @param id 
	     */
	    @OnOpen
	    public void onOpen(Session session, @PathParam("id") String id){
	        this.session = session;
	        webSocketSet.add(this);     //加入set中
	        addOnlineCount();           //在线数加1
	        this.id = id;
	        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount()+"-*-------"+id);
	     
	    }

	    /**
	     * 连接关闭调用的方法
	     */
	    @OnClose
	    public void onClose(){
	        webSocketSet.remove(this);  //从set中删除
	        subOnlineCount();           //在线数减1
	        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	    }

	    /**
	     * 收到客户端消息后调用的方法
	     * @param message 客户端发送过来的消息
	     * @param session 可选的参数
	     */
	    @OnMessage
	    public void onMessage(@PathParam("id") String id ,String message, Session session) {
	        System.out.println("来自客户端的消息:" + message);
/*			AbstractApplicationContext abs=new ClassPathXmlApplicationContext("applicationContext.xml");
			UserService image=abs.getBean(UserService.class);
			System.out.println(image);*/
	        //群发消息
	        for(WebSocketController item: webSocketSet){
	            try {
	            	System.out.println("------------------"+item.id+"-------------------------");
	            	if(item.id.equals(id)) {
	            		item.sendMessage(id+"大傻吊");
	            	}
	            } catch (IOException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }
	    }

	    /**
	     * 发生错误时调用
	     * @param session
	     * @param error
	     */
	    @OnError
	    public void onError(Session session, Throwable error){
	        System.out.println("发生错误");
	        error.printStackTrace();
	    }

	    /**
	     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	     * @param message
	     * @throws IOException
	     */
	    public void sendMessage(String message) throws IOException{
	        this.session.getBasicRemote().sendText(message);
	        System.out.println( this.session.getBasicRemote()+"------------------");
	        //this.session.getAsyncRemote().sendText(message);
	    }

	    public static synchronized int getOnlineCount() {
	        return onlineCount;
	    }

	    public static synchronized void addOnlineCount() {
	        WebSocketController.onlineCount++;
	    }

	    public static synchronized void subOnlineCount() {
	    	WebSocketController.onlineCount--;
	    }
	    
	    public static void sendInfo(String message,@PathParam("id") String id) throws IOException {
	        for (WebSocketController item : webSocketSet) {
	            try {
	                //这里可以设定只推送给这个id的，为null则全部推送
	                if(id==null) {
	                    item.sendMessage(message);
	                }else if(item.id.equals(id)){
	                    item.sendMessage(message);
	                }
	            } catch (IOException e) {
	                continue;
	            }
	        }
	    }
	}

