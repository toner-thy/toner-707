package com.cdthgk.bmp.info.vo;

/**
 *
 * <p>
 * 信息报送状态常量
 * </p>
 * <p>
 * 创建时间 2015-1-19 - 下午3:10:45
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class InfoConstant {

	/**
	 * 信息状态
	 * @author Administrator
	 *
	 */
	public static class InfoStatus {
		/**
		 * 草稿
		 */
		public static final Integer DRAFT = 1;
		/**
		 * 待审
		 */
		public static final Integer PENDING = 2;
		/**
		 * 审核通过
		 */
		public static final Integer PASS = 3;
		/**
		 * 审核驳回
		 */
		public static final Integer REFUSE = 4;
	}
	/**
	 * 信息上报状态
	 * @author Administrator
	 *
	 */
	public static class InfoReportStatus {
		/**
		 * 未上报
		 */
		public static final Integer UNREPORTED = 0;
		/**
		 * 已上报
		 */
		public static final Integer REPORTED = 1;
	}
	/**
	 * 信息操作日志状态
	 * @author Administrator
	 *
	 */
	public static class InfoOperateStatus {
		/**
		 * 录入
		 */
		public static final Integer DRAFT = 1;
		/**
		 * 提交审核（报审）
		 */
		public static final Integer PENDING = 2;
		/**
		 * 修改
		 */
		public static final Integer MODIFY = 3;
		/**
		 * 审核通过
		 */
		public static final Integer PASS = 4;
		/**
		 * 审核驳回
		 */
		public static final Integer REFUSE = 5;
		/**
		 * 上报
		 */
		public static final Integer REPORTED = 6;
	}
	/**
	 * 提醒状态
	 * @author Administrator
	 *
	 */
	public static class InfoWarnStatus {
		/**
		 * 草稿
		 */
		public static final Integer DRAFT = 0;
		/**
		 * 未查看
		 */
		public static final Integer UNVIEWED = 1;
		/**
		 * 查看
		 */
		public static final Integer VIEWED = 2;
	}
}