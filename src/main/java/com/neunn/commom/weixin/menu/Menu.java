package com.neunn.commom.weixin.menu;

import java.util.List;

public final class Menu {
	
	public static enum TYPE{
		click, view, scancode_waitmsg, scancode_push, pic_sysphoto, pic_photo_or_album, pic_weixin, location_select, media_id, view_limited
	} 
	
	private String type;
	
	private String name;
	
	private String key;
	
	private String url;
	
	private String media_id;
	
	private List<Menu> sub_button;
	
	
	private Menu(){
		
	}
	
	
	
	public String getType() {
		return type;
	}



	public String getName() {
		return name;
	}



	public String getKey() {
		return key;
	}



	public String getUrl() {
		return url;
	}



	public String getMedia_id() {
		return media_id;
	}



	public List<Menu> getSub_button() {
		return sub_button;
	}


	/**
	 * 创建事件型菜单
	 * @param type	事件类型
	 * @param name	菜单显示名称
	 * @param key	事件Key
	 * @return
	 */
	public static Menu createEventMenu(String type, String name, String key){
		Menu menu = new Menu();
		menu.type = type;
		menu.name = name;
		menu.key = key;
		return menu;
	}
	
	/**
	 * 创建带有子菜单的一级菜单按钮
	 * @param name		菜单显示名
	 * @param subButton	子菜单
	 * @return
	 */
	public static Menu createButton(String name, List<Menu> subButton){
		Menu menu = new Menu();
		menu.name = name;
		menu.sub_button = subButton;
		return menu;
	}
	
	/**
	 * 创建用于页面跳转的菜单
	 * @param name	菜单显示名
	 * @param url	需要调准的URL
	 * @return
	 */
	public static Menu createViewMenu(String name, String url){
		Menu menu = new Menu();
		menu.type = TYPE.view.toString();
		menu.name = name;
		menu.url = url;
		return menu;
	}
	   /**
     * 创建菜单跳转图文
     * @param name  菜单显示名
     * @param url   需要调准的URL
     * @return
     */
    public static Menu createNews(String name, String mediaId){
        Menu menu = new Menu();
        menu.type = TYPE.view_limited.toString();
        menu.name = name;
        menu.media_id = mediaId;
        return menu;
    }

}
