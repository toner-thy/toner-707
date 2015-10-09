var _tbp_version = 0.2;
var Panel = new Class({
		Implements : [Options, Events],
		options : {
			loadType : "iframe",
			title : "",
			url : ""
		},
		initialize : function (a, b) {
			if (this.container = $(a)) {
				this.setOptions(b);
				this.content = new Element("div", {
						"class" : "ff_panel",
						styles : {
							height : "100%",
							width : "100%",
							pozition : "relative"
						}
					});
				a.adopt(this.content);
			}
		},
		getContentWindow : function () {
			return window[this.content.getFirst().get("id")];
		},
		destroy : function () {
			this.content.destroy();
		},
		load : function (b) {
			if (!b) {
				b = this.options.url;
			}
			if (this.options.loadType == "iframe") {
				var c = this;
				if (this.ifr) {
					this.content.getFirst().src = b;
				} else {
					var a = "IFrame_" + new Date().getTime();
					new Element("iframe", {
						id : a,
						name : a,
						src : b,
						frameborder : 0,
						styles : {
							width : "100%",
							height : "100%",
							border : "none"
						},
						events : {
							load : function () {
								c.fireEvent("load", this);
							}
						}
					}).inject(this.content);
					this.ifr = true;
				}
			}
		}
	});
var TabPanel = new Class({
		Implements : [Options, Events],
		items : [],
		options : {
			loadType : "iframe",
			titlePanelHeight : 18,
			max : 10,
			maxPolicy : "replace",
			ignoreLoadedUrl : true,
			overrideLoadedUrl : true,
			autoResize : true,
			title : "default",
			url : ""
		},
		initialize : function (a, b) {
			if (this.container = $(a)) {
				this.setOptions(b);
				this.tabPanel = new Element("div", {
						"class" : "ff_tab_panel"
					});
				this.tabTitlePanel = new Element("div", {
						"class" : "ff_tab_title_panel"
					});
				var c = this.container.getSize().y - this.options.titlePanelHeight;
				this.tabContentPanel = new Element("div", {
						"class" : "ff_tab_content_panel",
						styles : {
							height : c - 5
						}
					});
				if (this.options.autoResize) {
					this.autoResize();
				}
				this.tabPanel.adopt(this.tabTitlePanel);
				this.tabPanel.adopt(this.tabContentPanel);
				this.container.adopt(this.tabPanel);
				this.add({
					title : b.title,
					url : b.url,
					close : false,
					onClose : b.onClose
				});
			}
		},
		add : function (o) {
			var c = this;
			if (this.items.length == this.options.max) {
				if (this.options.maxPolicy == "replace") {
					//if (confirm("打开选项卡超过最大允许的数量[" + this.options.max + "]，打开过多选项卡会影响浏览器性能，是否替换最早打开的选项卡？选择是将会在最早打开的选项卡载入当前页，选否则不进行任何操作")) {
					if (confirm("打开过多选项卡会影响浏览器性能，是否在最左侧选项卡打开？选择“确定”将会在最左侧选项卡打开，选“取消”则不进行任何操作")) {
						for (var e = 0; e < this.items.length; e++) {
							var m = this.items[e];
							if (m.close) {
								this.active(e);
								this.load({
									url : o.url,
									title : ""
								});
								return;
							}
						}
						return;
					} else {
						return;
					}
				} else {
					if (this.options.maxPolicy == "close") {
						if (confirm("打开选项卡超过最大允许的数量[" + this.options.max + "]，打开过多选项卡会影响浏览器性能，是否关闭其他选项卡？选择是将会关闭其他选项卡，选择否将不进行任何操作")) {
							this.closeAll();
						} else {
							return;
						}
					} else {
						alert("打开选项卡超过最大允许的数量[" + this.options.max + "]，打开过多选项卡会影响浏览器性能，你可以先关闭其他选项卡");
						return;
					}
				}
			}
			var k = o.title;
			var b = o.url;
			var l = true;
			if (!o.onClose) {
				o.onClose = function () {};
			}
			if (o.close === false) {
				l = false;
			}
			if (this.options.ignoreLoadedUrl) {
				var f = false;
				this.items.each(function (p, i) {
					if (p.content.options.url == b) {
						this.activeIndex = i;
						this.active();
						f = true;
					}
				}
					.bind(this));
				if (f) {
					return;
				}
			}
			var a = new Element("span", {
					"class" : "ff_tab_title_item"
				});
			var n = new Element("span", {
					"class" : "ff_tab_title_item_text",
					text : k
				});
			a.adopt(n);
			this.tabTitlePanel.adopt(a);
			var h = new Panel(this.tabContentPanel, {
					url : b
				});
			h.addEvent("load", function (i) {
				if (i && i.name) {
					if (!n.get("text")) {
						n.set("text", (window[i.name].document.title));
					}
					if (c.options.overrideLoadedUrl) {
						this.options.url = window[i.name].location.pathname + window[i.name].location.search;
					}
				}
			});
			h.content.setStyle("display", "block");
			var g = {
				id : "item_id_" + $time(),
				title : a,
				content : h,
				activingItem : this.getActiveItem(),
				active : false,
				close : l,
				onClose : o.onClose,
				setTitle : function (i) {
					n.set("text", i);
				},
				getTitle : function (i) {
					return n.get("text");
				}
			};
			this.items.push(g);
			if (l) {
				var j = new Element("a", {
						"class" : "ff_tab_title_item_close",
						html : "&nbsp;&nbsp;&nbsp;"
					});
				a.adopt(j);
				j.addEvent("click", function () {
					var i = arguments[0];
					this.close(i);
				}
					.bind(this, this.items[this.items.length - 1]));
			}
			a.addEvent("click", function () {
				if (!arguments[0].active) {
					var i = arguments[0];
					this.items.each(function (q, p) {
						if (i == arguments[0]) {
							this.activeIndex = p;
							this.active();
						}
					}
						.bind(this));
				}
			}
				.bind(this, this.items[this.items.length - 1]));
			this.activeIndex = this.items.length - 1;
			this.active();
			this.load();
			var d = [];
			if (l) {
				d.push({
					name : "关闭当前选项卡",
					onAction : function () {
						c.closeActive();
					}
				});
			}
			d.push({
				name : "关闭所有选项卡",
				onAction : function () {
					c.closeAll();
				}
			});
			new Mif.Menu({
				initialize : function () {
					new Mif.Menu.KeyNav(this);
				},
				contextmenu : true,
				target : a,
				list : {
					items : d
				}
			});
		},
		close : function (b) {
			var c = this.getActiveItem();
			this._close(b);
			if (c) {
				if (c == b) {
					this.activeIndex = this.activeIndex - 1;
				} else {
					for (var a = 0; a < this.items.length; a++) {
						if (this.items[a] == c) {
							this.activeIndex = a;
							break;
						}
					}
				}
			} else {
				this.activeIndex = (this.activeIndex - 1 < 0) ? 0 : this.activeIndex - 1;
			}
			this.active();
		},
		_close : function (a) {
			try {
				if (a.onClose(this, a) === false) {
					return;
				}
			} catch (b) {}

			a.title.destroy();
			a.content.destroy();
			a.content = null;
			this.items.erase(a);
			a = null;
		},
		closeAll : function () {
			var b = this;
			var a = [];
			this.items.each(function (c) {
				a.push(c);
			});
			a.each(function (c) {
				if (c.close) {
					b._close(c);
				}
			});
			this.activeIndex = 0;
			this.active();
		},
		closeActive : function () {
			var a = this.getActiveItem();
			this.close(a);
		},
		active : function (a) {
			if (a) {
				this.activeIndex = a;
			}
			this.items.each(function (c, b) {
				if (this.activeIndex == b) {
					c.active = true;
					c.title.addClass("ff_tab_title_item_active");
					c.content.content.setStyle("display", "block");
				} else {
					c.active = false;
					c.title.removeClass("ff_tab_title_item_active");
					c.content.content.setStyle("display", "none");
				}
			}
				.bind(this));
		},
		getActiveItem : function () {
			if (this.items.length > 0) {
				var a = null;
				this.items.each(function (c, b) {
					if (c.active) {
						a = c;
					}
				});
				if (a) {
					return a;
				}
				this.activeIndex = 0;
				this.active();
				return this.items[0];
			}
		},
		getActiveItemLoader : function () {
			return this.getActiveItem().content.content.getFirst();
		},
		getActivingItem : function () {
			return this.getActiveItem().activingItem;
		},
		getActivingItemContent : function () {
			var a = this.getActivingItem().content;
			if (a) {
				return a.getContentWindow();
			}
			return null;
		},
		isLoaded : function (b) {
			var a = false;
			this.items.each(function (d, c) {
				if (d.content.options.url == b) {
					a = true;
				}
			});
			return a;
		},
		load : function (b) {
			var a = null;
			if (b) {
				this.setTitle(b.title);
				if (b.url) {
					a = b.url;
				}
			}
			this.getActiveItem().content.load(a);
		},
		setTitle : function (a) {
			if (a !== undefined) {
				this.getActiveItem().setTitle(a);
			}
		},
		autoResize : function () {
			window.addEvent("resize", function () {
				var a = this.container.getSize().y - this.options.titlePanelHeight;
				this.tabContentPanel.setStyle("height", a - 5);
			}
				.bind(this));
		}
	});
