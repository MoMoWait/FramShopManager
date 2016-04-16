/**
 * 
 */
package cn.edu.fjnu.shop.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.edu.fjnu.shop.config.Config;
import cn.edu.fjnu.shop.domain.Information;
import cn.edu.fjnu.shop.service.InformationService;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author GaoFei
 * 
 */
public class InformationManagerAction extends ActionSupport {

	private String des;
	private File photoFile;
	private String photoFileFileName;
	private String photoFileContentType;
	private static final String masterSecret = "0e38adf3cd0d803bc0d8c9c4";
	private static final String appKey = "7860b417dda43ed99dcfd711";
	public static final String TITLE = "Test from API example";
	public static String ALERT = "Test from API Example - alert";
	public static final String MSG_CONTENT = "Test from API Example - msgContent";
	public static final String REGISTRATION_ID = "0900e8d85ef";
	public static final String TAG = "tag_api";

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String informationManager() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("informationManager");
		return "success";
	}

	public String commitAdd() throws Exception {
		// TODO Auto-generated method stub
		Information information = new Information();
		String photoUrl=null;
		String msg="";
		if (photoFile != null) {
			// 生成文件名
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/image/postphoto");
			// System.out.println(realpath);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyyMMddhhmmssSSS");
			// String fileName=""
			Date date = new Date();
			String fileName = simpleDateFormat.format(date);
			fileName += photoFileFileName
					.substring(photoFileFileName.length() - 4);
			File saveFile = new File(realpath, fileName);
			// 判断上传目录是否存在
			if (!saveFile.getParentFile().exists())
				saveFile.getParentFile().mkdirs();
			// 存储文件
			FileUtils.copyFile(photoFile, saveFile);
			photoUrl=Config.serverIP+"/FramShopManager/image/postphoto/"
					+ fileName;
			information
					.setPhoto(Config.serverIP+"/FramShopManager/image/postphoto/"
							+ fileName);
			// product.setProductPhoto("http://112.74.77.24:8080/FramShopManager/image/product/"+fileName);
			
			//msg+=photoUrl;
			//msg+="#";
		}

		information.setDes(des);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddhhmmssSSS");
		Date date = new Date();
		String postTime = simpleDateFormat.format(date);
		information.setPostTime(postTime);
		InformationService informationService = new InformationService();
		informationService.addInformation(information);
		msg+=des;
		if(photoUrl!=null){
			msg+="#";
			msg+=photoUrl;
		}
		ALERT=msg;
		// 发送至Android端
		SendPush();
		return "success";
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public File getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(File photoFile) {
		this.photoFile = photoFile;
	}

	public String getPhotoFileFileName() {
		return photoFileFileName;
	}

	public void setPhotoFileFileName(String photoFileFileName) {
		this.photoFileFileName = photoFileFileName;
	}

	public String getPhotoFileContentType() {
		return photoFileContentType;
	}

	public void setPhotoFileContentType(String photoFileContentType) {
		this.photoFileContentType = photoFileContentType;
	}

	public static void SendPush() {
		// HttpProxy proxy = new HttpProxy("localhost", 3128);
		// Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = buildPushObject_all_all_alert();

		try {
			PushResult result = jpushClient.sendPush(payload);
			//LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			//LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			/*LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());*/
		}
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias("alias1"))
				.setNotification(Notification.alert(ALERT)).build();
	}

	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag("tag1"))
				.setNotification(Notification.android(ALERT, TITLE, null))
				.build();
	}

	public static PushPayload buildPushObject_android_and_ios() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag("tag1"))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert("alert content")
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setTitle("Android Title")
												.build())
								.addPlatformNotification(
										IosNotification
												.newBuilder()
												.incrBadge(1)
												.addExtra("extra_key",
														"extra_value").build())
								.build()).build();
	}

	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.tag_and("tag1", "tag_all"))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(ALERT).setBadge(5)
												.setSound("happy")
												.addExtra("from", "JPush")
												.build()).build())
				.setMessage(Message.content(MSG_CONTENT))
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.tag("tag1", "tag2"))
								.addAudienceTarget(
										AudienceTarget
												.alias("alias1", "alias2"))
								.build())
				.setMessage(
						Message.newBuilder().setMsgContent(MSG_CONTENT)
								.addExtra("from", "JPush").build()).build();
	}

}
