package org.xutils;

import android.app.Application;

import org.xutils.view.ViewInjectorImpl;


/**
 * Created by wyouflf on 15/6/10.
 * 任务控制中心, http, image, db, view注入等接口的入口.
 * 需要在在application的onCreate中初始化: x.Ext.init(this);
 */
public final class x {

    private x() {
    }

    public static boolean isDebug() {
        return Ext.debug;
    }

	public static Application app() {
		if (Ext.app == null) {
			throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate()");
		}
		return Ext.app;
	}

	public static ViewInjector view() {
		if (Ext.viewInjector == null) {
			ViewInjectorImpl.registerInstance();
		}
		return Ext.viewInjector;
	}


	public static class Ext {
		private static boolean debug;
		private static Application app;
		private static ViewInjector viewInjector;

		private Ext() {
		}

		public static void init(Application app) {
			if (Ext.app == null) {
				Ext.app = app;
			}
		}

		public static void setDebug(boolean debug) {
			Ext.debug = debug;
		}


		public static void setViewInjector(ViewInjector viewInjector) {
			Ext.viewInjector = viewInjector;
		}
	}
}
