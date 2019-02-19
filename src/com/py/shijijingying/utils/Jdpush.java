package com.py.shijijingying.utils;

import java.util.Map;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class Jdpush {

	private static String appKey = "28ccbb56402f467448794e64";
	private static String masterSecret = "d4d22ad43ae835fdfeb298ca";

	private static String roadTourAppKey = "cb64775135b6eceec684cd42";
	private static String roadTourMasterSecret = "55c31f0c633e35daf6227b7c";
	
	// 极光推送>>Android
		// Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
		public static void jpushAndroid(Map<String, String> parm, Audience audience) {
			// 设置好账号的app_key和masterSecret
			// 创建JPushClient(极光推送的实例)
			JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			// 推送的关键,构造一个payload
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
					.setAudience(audience)// 你项目中的用户
					.setNotification(Notification.android(parm.get("body"), parm.get("title"), parm))
					// 发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
					.setOptions(Options.newBuilder().setApnsProduction(true).build())
					// 这里是指定开发环境,不用设置也没关系
					.setMessage(Message.newBuilder().setTitle(parm.get("title")).setMsgContent(parm.get("body")).build())// 自定义信息
					.build();

			try {
				PushResult pu = jpushClient.sendPush(payload);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}

		

			// 极光推送>>ios
			// Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
			public static void jpushIOS(Map<String, String> parm, Audience audience) {
				// 创建JPushClient
				JPushClient jpushClient = new JPushClient(masterSecret, appKey);
				
				IosAlert alert = IosAlert.newBuilder()
			            .setTitleAndBody(parm.get("title"), null, parm.get("body"))
			            .setActionLocKey("PLAY")
			            .build();
				
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
						.setAudience(audience)// 你项目中的用户
						.setNotification(
								Notification.newBuilder()
										.addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
												.setBadge(+1).setSound("happy")// 这里是设置提示音(更多可以去官网看看)
												.addExtras(parm).build())
										.build())
						.setOptions(Options.newBuilder().setApnsProduction(true).build())
						.setMessage(Message.newBuilder().setTitle(parm.get("title")).setMsgContent(parm.get("body"))
								.addExtras(parm).build())// 自定义信息
						.build();

				try {
					PushResult pu = jpushClient.sendPush(payload);

				} catch (APIConnectionException e) {
					e.printStackTrace();
				} catch (APIRequestException e) {
					e.printStackTrace();
				}
			}
		
		// 极光推送>>All所有平台
		public static void jpushAll(Map<String, String> parm) {
			// 设置好账号的ACCESS_KEY和SECRET_KEY

			// 创建JPushClient
			JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			// 创建option

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()) // 所有平台的用户
					.setAudience(Audience.registrationId(parm.get("RegId")))// registrationId指定用户
					.setNotification(Notification.newBuilder()
							.addPlatformNotification(IosNotification.newBuilder().setAlert(parm.get("title")).setBadge(+1)
									.setSound("happy").addExtras(parm).build())
							.addPlatformNotification(
									AndroidNotification.newBuilder().addExtras(parm).setAlert(parm.get("body")).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(true).build())// 指定开发环境
					.setMessage(Message.newBuilder().setMsgContent(parm.get("body")).addExtras(parm).build())// 自定义信息
					.build();
			try {
				PushResult pu = jpushClient.sendPush(payload);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 极光推送>>ios
		// Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
		public static void roadTourjpushIOS(Map<String, String> parm, Audience audience) {
			// 创建JPushClient
			JPushClient jpushClient = new JPushClient(roadTourMasterSecret, roadTourAppKey);
			
			IosAlert alert = IosAlert.newBuilder()
		            .setTitleAndBody(parm.get("title"), null, parm.get("body"))
		            .setActionLocKey("PLAY")
		            .build();
			
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
					.setAudience(audience)// 你项目中的用户
					.setNotification(
							Notification.newBuilder()
									.addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
											.setBadge(+1).setSound("happy")// 这里是设置提示音(更多可以去官网看看)
											.addExtras(parm).build())
									.build())
					.setOptions(Options.newBuilder().setApnsProduction(true).build())
					.setMessage(Message.newBuilder().setTitle(parm.get("title")).setMsgContent(parm.get("body"))
							.addExtras(parm).build())// 自定义信息
					.build();

			try {
				PushResult pu = jpushClient.sendPush(payload);

			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
			
		// 极光推送>>Android
		// Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
		public static void roadTourjpushAndroid(Map<String, String> parm, Audience audience) {
			// 设置好账号的app_key和masterSecret
			// 创建JPushClient(极光推送的实例)
			JPushClient jpushClient = new JPushClient(roadTourMasterSecret, roadTourAppKey);
			// 推送的关键,构造一个payload
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
					.setAudience(audience)// 你项目中的用户
					.setNotification(Notification.android(parm.get("body"), parm.get("title"), parm))
					// 发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
					.setOptions(Options.newBuilder().setApnsProduction(true).build())
					// 这里是指定开发环境,不用设置也没关系
					.setMessage(Message.newBuilder().setTitle(parm.get("title")).setMsgContent(parm.get("body")).build())// 自定义信息
					.build();

			try {
				PushResult pu = jpushClient.sendPush(payload);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
