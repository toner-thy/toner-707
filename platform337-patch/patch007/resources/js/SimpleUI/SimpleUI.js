var SimpleUI = {
    version: 0.4
};
SimpleUI.Overlay = new Class({
    Implements: [Options],
    getOptions: function() {
        return {
            loading: false,
            opacity: 0.6,
            zIndex: 1,
            effectOut: true,
            effectIn: false,
            link: "chain",
            duration: 300,
            container: document.body,
            cover: document.body,
            onClick: function() {}
        };
    },
    initialize: function(a) {
        this.setOptions(this.getOptions(), a);
        this.options.container = $(this.options.container);
        this.options.cover = $(this.options.cover);
        var b = $time();
        this.container = new Element("div", {
            id: "OverlayContainer_" + b,
            styles: {
                position: "absolute",
                left: "0px",
                top: "0px",
                zIndex: this.options.zIndex
            }
        }).injectInside(this.options.container);
        if (Browser.Engine.trident) {
            this.iframe = new Element("iframe", {
                id: "OverlayIframe_" + b,
                name: "OverlayIframe_" + b,
                src: "",
                frameborder: 0,
                scrolling: "no",
                styles: {
                    position: "absolute",
                    top: 0,
                    left: 0,
                    bottom: 0,
                    right: 0,
                    width: "100%",
                    height: "100%",
                    filter: "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)",
                    opacity: 0,
                    zIndex: this.options.zIndex
                }
            }).injectInside(this.container);
        }
        this.overlay = new Element("div", {
            id: "Overlay_" + b,
            "class": this.options.loading ? "easyui_overlay easyui_overlay_loading": "easyui_overlay",
            styles: {
                position: "absolute",
                left: "0px",
                top: "0px",
                width: "100%",
                height: "100%",
                zIndex: this.options.zIndex + 1
            }
        }).injectInside(this.container);
        this.container.addEvent("click",
        function() {
            this.options.onClick(this);
        }.bind(this));
        this.fade = new Fx.Tween(this.container, {
            duration: this.options.duration,
            link: this.options.link,
            property: "opacity",
            onComplete: function() {
                if (this.element.get("opacity") == 0) {
                    this.element.setStyle("display", "none");
                } else {
                    this.element.setStyle("display", "");
                }
            }
        }).set(0);
        this.display = "hidden";
        this.position();
        window.addEvent("resize", this.position.bind(this));
    },
    position: function() {
        this.options.cover = $(this.options.cover);
        if (this.options.cover == document.body) {
            this.container.setStyles({
                top: 0,
                bottom: 0,
                height: $(document.body).getSize().y,
                left: 0,
                right: 0,
                width: $(document.body).getSize().x
            });
        } else {
            var b = this.options.cover.getCoordinates();
            var a = this.options.cover.getScroll();
            if (Browser.Engine.trident && this.options.cover.get("tag") != "body") {
                this.container.setStyles({
                    top: b.top + a.y - 2,
                    height: b.height,
                    left: b.left + a.x - 2,
                    width: b.width
                });
            } else {
                this.container.setStyles({
                    top: b.top + a.y,
                    height: b.height,
                    left: b.left + a.x,
                    width: b.width
                });
            }
        }
    },
    setZIndex: function(a) {
        this.container.setStyle("zIndex", a);
        if (Browser.Engine.trident) {
            this.iframe.setStyle("zIndex", a);
        }
        this.overlay.setStyle("zIndex", a + 1);
    },
    show: function() {
        if (Browser.Engine.trident) {
            this.iframe.setStyle("opacity", 1);
        }
        if (this.display == "hidden") {
            if (this.options.cover != document.body) {
                this.position();
            }
            this.display = "show";
            if (this.options.effectIn) {
                this.fade.start(0, this.options.opacity);
            } else {
                this.fade.set(this.options.opacity);
                this.container.setStyle("display", "");
            }
        }
    },
    hide: function() {
        if (Browser.Engine.trident) {
            this.iframe.setStyle("opacity", 0);
        }
        if (this.display == "show") {
            this.display = "hidden";
            if (this.options.effectOut) {
                this.fade.start(this.options.opacity, 0);
            } else {
                this.fade.set(0);
                this.container.setStyle("display", "none");
            }
        }
    },
    destroy: function() {
        this.container.destroy();
    }
});
SimpleUI.SimpleTab = new Class({
    Implements: [Options, Events],
    getOptions: function() {
        return {
            el: "",
            active: 0,
            area: 200,
            speed: 0.1,
            autoResize: false,
            fixHeight: false,
            btnEl: "",
            itemActiveClass: "panel_title_active",
//            switchEvent: "mouseenter",
//  陶汇源 2014-04-03 修改默认鼠标移动事件为，点击事件
            switchEvent: "click",
            autoActive: true
        };
    },
    initialize: function(a) {
        this.setOptions(this.getOptions(), a);
        this.panel = $(this.options.el);
        var c = this;
        this.panel.items = this.panel.getFirst().getFirst().getChildren();
        if (this.options.btnEl && $(this.options.btnEl)) {
            this.panel.buttons = $(this.options.btnEl).getChildren();
            this.panel.buttons.setStyle("display", "none");
        } else {
            if (this.panel.getFirst().getChildren(".btn_bar") && this.panel.getFirst().getChildren(".btn_bar").length > 0 && this.panel.getFirst().getChildren(".btn_bar")[0] != this.panel.getFirst().getFirst()) {
                this.panel.buttons = this.panel.getFirst().getChildren(".btn_bar")[0].getChildren();
                this.panel.buttons.setStyle("display", "none");
            }
        }
        var b = 0;
        this.panel.items.each(function(f, e) {
            if (c.options.switchEvent != "mouseenter") {
                c.options.switchEvent = "click";
            }
            f.addEvent(c.options.switchEvent,
            function() {
                c.active(this);
            });
            b += f.getComputedSize().totalWidth + 0.5;
        });
        var d = this.panel.getFirst();
        if (d.measure(function() {
            return this.getSize();
        }).x < b) {
            d.getFirst().setStyle("width", b);
            this.scrollItemsAreaFx = new Scroller($(d), {
                area: this.options.area,
                velocity: this.options.speed,
                direction: "x"
            });
            $(d).setStyle("overflow", "hidden");
            $(d).addEvent("mouseenter", this.scrollItemsAreaFx.start.bind(this.scrollItemsAreaFx));
            $(d).addEvent("mouseleave", this.scrollItemsAreaFx.stop.bind(this.scrollItemsAreaFx));
        }
        this.panel.contents = this.panel.getChildren(".tab_panel_content")[0].getChildren("div");
        if (this.options.autoResize) {
            window.addEvent("resize", this.resize);
        }
        this.resize();
        if (this.options.autoActive) {
            this.active(this.options.active);
        }
    },
    resize: function() {
        if (this.options.fixHeight) {
            var a = this;
            this.panel.contents.each(function(c, b) {
                c.setStyles({
                    height: a.panel.measure(function() {
                        return this.getSize();
                    }).y - a.panel.items[b].measure(function() {
                        return this.getSize();
                    }).y
                });
            });
        }
    },
    active: function(b) {
        if (!b) {
            b = this.options.active;
        }
        if (this.activeIndex == b || this.activeItem == b) {
            return;
        }
        var c = this;
        var a = this.panel;
        a.items.each(function(e, d) {
            if (e == b || d == b) {
                c.activeIndex = d;
                c.activeItem = e;
                a.items.removeClass(c.options.itemActiveClass);
                e.addClass(c.options.itemActiveClass);
                a.contents.setStyle("display", "none");
                a.contents[d].setStyle("display", "block");
                if ($ENV.browser.isIE6) {
                    a.contents[d].setStyle("zoom", 1);
                }
                if (!e.retrieve("init")) {
                    c.fireEvent("firstActive", [e, a.contents[d], d]);
                    e.store("init", true);
                }
                c.fireEvent("active", [e, a.contents[d], d]);
            }
        });
        if (a.buttons) {
            a.buttons.setStyle("display", "none");
            a.buttons[this.activeIndex].setStyle("display", "");
        }
    },
    destroy: function() {
        this.panel.destroy();
    }
});
SimpleUI.SimpleTabs = new Class({
    Implements: [Options],
    options: {
        el: ""
    },
    initialize: function(a) {
        this.setOptions(this.options, a);
        var c = $$(this.options.el);
        var b = [];
        c.each(function(e) {
            var d = $merge(this.options, {
                el: e,
                autoActive: false
            });
            b.push(new SimpleUI.SimpleTab(d));
        }.bind(this));
        b.each(function(d) {
            d.active();
        });
    }
});
SimpleUI.Panel = new Class({
    Implements: [Options, Events],
    getOptions: function() {
        return {
            container: document.body,
            loadType: "iframe",
            topClass: "panel_content",
            idSign: "panel_content",
            icon: null,
            mask: true,
            params: {},
            alwaysMask: false,
            title: "",
            titleHeight: 31,
            autoScroll: true,
            url: "",
            width: 600,
            height: 400
        };
    },
    initialize: function(a) {
        if (a.container) {
            a.container = $(a.container);
        }
        this.options = this._fixedSize(a);
        this.setOptions(this.getOptions(), a);
        this._initView();
        if (this.options.mask) {
            this.maskOverlay = new SimpleUI.Overlay({
                maskWithIframe: false,
                zIndex: this.options.zIndex + 1,
                cover: this.contentPanel,
                loading: true
            });
        }
    },
    _fixedSize: function(a) {
        if (a) {
            if (a.width && a.width < 1) {
                a.width = window.getSize().x * a.width;
            }
            if (a.height && a.height < 1) {
                a.height = window.getSize().y * a.height;
            }
        }
        return a;
    },
    _initView: function() {
        var a = $time();
        this.id = this.options.idSign + a;
        this.panel = new Element("div", {
            "class": this.options.topClass,
            id: this.id,
            styles: {
                width: this.options.width
            }
        }).addEvent("mousedown",
        function() {
            this.fireEvent("active");
        }.bind(this)).injectInside(this.options.container);
        this._headerPanelId = "headerPanel_" + a;
        this._headerPanelTextId = "headerPanelText_" + a;
        this._headerPanelIconId = "headerPanelIcon" + a;
        var b = new Element("div", {
            "class": "panel_btn_bar"
        });
        new Element("div", {
            "class": "btn btn_close"
        }).addEvent("click",
        function() {
            this.close();
        }.bind(this)).addEvent("mouseenter",
        function() {
            this.addClass("btn_close_hover");
        }).addEvent("mouseleave",
        function() {
            this.removeClass("btn_close_hover");
        }).injectTop(b);
        if (this.options.max) {
            new Element("div", {
                "class": "btn btn_max"
            }).addEvent("click",
            function() {
                this._max();
            }.bind(this)).addEvent("mouseenter",
            function() {
                this.addClass("btn_max_hover");
            }).addEvent("mouseleave",
            function() {
                this.removeClass("btn_max_hover");
            }).injectTop(b);
            new Element("div", {
                "class": "btn btn_resume",
                styles: {
                    display: "none"
                }
            }).addEvent("click",
            function() {
                this.resume();
            }.bind(this)).addEvent("mouseenter",
            function() {
                this.addClass("btn_resume_hover");
            }).addEvent("mouseleave",
            function() {
                this.removeClass("btn_resume_hover");
            }).injectTop(b);
        }
        if (this.options.min) {
            new Element("div", {
                "class": "btn btn_min"
            }).addEvent("click",
            function() {
                this.hide();
            }.bind(this)).addEvent("mouseenter",
            function() {
                this.addClass("btn_min_hover");
            }).addEvent("mouseleave",
            function() {
                this.removeClass("btn_min_hover");
            }).injectTop(b);
        }
        this._titlePanel = new Element("div", {
            "class": "panel_title",
            text: this.options.title
        });
        this.headerPanel = new Element("div", {
            "class": "panel_header"
        }).adopt(this._titlePanel).adopt(b).injectInside(this.panel);
        this.contentPanel = new Element("div", {
            "class": "panel_content",
            styles: {
                width: this.options.width,
                height: this.options.height - this.options.titleHeight
            }
        }).injectInside(this.panel);
    },
    resize: function(a) {
        a = $merge(this.options, this._fixedSize(a));
        this.options = a;
        this.panel.setStyles({
            width: a.width,
            height: a.height
        });
        this.contentPanel.setStyles({
            width: a.width - 2,
            height: this.options.height - this.options.titleHeight - 3
        });
    },
    setTitle: function(a) {
        this._titlePanel.set("text", a);
    },
    setIcon: function(a) {
        $$(".header_center_text").setStyle("padding-left", 24);
        $(this._headerPanelIconId).setStyle("display", "block").set("src", a);
    },
    getTitlebarEl: function() {
        return $(this._headerPanelId);
    },
    load: function(c) {
        var e = window;
        if (c && c.opener) {
            e = c.opener;
            delete c.opener;
        }
        c = $merge(this.options, this._fixedSize(c));
        this.options = c;
        this.setTitle(c.title);
        var b = this;
        var a = {
            comp: b,
            opener: e,
            params: b.options.params
        };
        if (this.options.loadType == "iframe") {
            var d = function() {};
            if (this.options.mask) {
                this.maskOverlay.show();
                this.contentPanel.setStyle("visibility", "hidden");
                d = function() {
                    this.contentPanel.setStyle("visibility", "visible");
                    this.maskOverlay.hide();
                }.bind(this);
            }
            var f = function() {
                d();
                b.fireEvent("load");
                var h = window[this.get("name")];
                if (!b.options.title) {
                    b.setTitle(h.document.title);
                }
                h.getOpener = function() {
                    return e;
                };
                h.close = function() {
                    b.close();
                };
                h.getParams = function() {
                    return b.options.params;
                };
                if (h.onLoadReady) {
                    h.onLoadReady(a);
                } else {
                    if (h.onDialogReady) {
                        h.onDialogReady(a);
                    }
                }
                if (b.options.alwaysMask) {
                    var i = function() {
                        b.maskOverlay.show();
                        b.contentPanel.setStyle("visibility", "hidden");
                    };
                    if (h.attachEvent) {
                        h.attachEvent("onunload", i);
                    } else {
                        h.onunload = i;
                    }
                }
            };
            if (this._ifrInit) {
                this.contentPanel.getFirst().src = c.url;
                this.contentPanel.getFirst().removeEvent("load", this._onload);
                this.contentPanel.getFirst().addEvent("load", f);
            } else {
                var g = "hidden";
                if (this.options.autoScroll) {
                    g = "auto";
                }
                this.contentPanel.adopt(new IFrame({
                    src: c.url,
                    frameborder: 0,
                    styles: {
                        width: "100%",
                        height: "100%",
                        overflow: g
                    },
                    events: {
                        load: f
                    }
                }));
                this._ifrInit = true;
            }
            this._onload = f;
        } else {
            if (this.options.loadType == "inline") {
                this.contentPanel.set("html", $(c.url).get("html"));
                b.fireEvent("load");
            } else {
                if (this.options.loadType == "xhr") {
                    var d = function() {};
                    if (this.options.mask) {
                        this.maskOverlay.show();
                        this.contentPanel.setStyle("visibility", "hidden");
                        d = function() {
                            this.contentPanel.setStyle("visibility", "visible");
                            this.maskOverlay.hide();
                        }.bind(this);
                    }
                    var f = function() {
                        d();
                        b.fireEvent("load");
                        if (window.onLoadReady) {
                            window.onLoadReady(a);
                        } else {
                            if (window.onDialogReady) {
                                window.onDialogReady(a);
                            }
                        }
                    };
                    this.contentPanel.set("load", {
                        onComplete: f
                    });
                    this.contentPanel.load(c.url);
                } else {
                    throw new Error("未实现的loadType -> " + this.options.loadType);
                }
            }
        }
        return this;
    },
    destroy: function() {
        this.panel.destroy();
    }
});
SimpleUI.Window = new Class({
    Extends: SimpleUI.Panel,
    options: {
        x: 0,
        y: 0,
        zIndex: 10,
        center: "no",
        modal: true,
        closeAction: "hide",
        autoShow: false,
        autoMax: false,
        acitveOnTop: false,
        move: false,
        min: true,
        max: true,
        close: true,
        resize: false
    },
    initialize: function(a) {
        if (a.container) {
            delete a.container;
        }
        a.idSign = "panel";
        a.topClass = a.idSign;
        a = $merge(this.options, this._fixedSize(a));
        this.parent(a);
        this.setOptions(a);
        this.panel.setStyles({
            position: "absolute",
            display: "none"
        });
        if (this.options.modal) {
            this.overlay = new SimpleUI.Overlay({
                zIndex: this.options.zIndex - 1
            });
        }
        if (this.options.move) {
            this.dragable = new Drag(this.id, {
                snap: 5,
                handle: this.getTitlebarEl(),
                onStart: function(b) {
                    b.setStyle("cursor", "move");
                },
                onComplete: function(b) {
                    b.setStyle("cursor", "");
                }
            });
        }
        if (this.options.autoShow) {
            this.show();
        }
        if (this.options.acitveOnTop) {
            this.panel.addEvent("mousedown",
            function() {
                this.active();
            }.bind(this));
        }
        this._position(this.options);
        window.addEvent("resize", (function() {
            var b = this;
            return function() {
                b._rePosition(b.options);
            };
        }).apply(this));
    },
    active: function() {
        var a = SimpleUI.Window.maxZindex;
        var b = this.getZindex();
        if (a < b) {
            a = b;
        } else {
            a += 1;
            this.setZindex(a);
        }
        SimpleUI.Window.maxZindex = a;
    },
    _rePosition: function(a) {
        if (this.options.center != "no") {
            this._position(a);
        }
    },
    _position: function(c) {
        c = $merge(this.options, this._fixedSize(c));
        this.options = c;
        this.setZindex(c.zIndex);
        if (this.options.center == "no") {
            this.move(c.x, c.y);
        } else {
            var d = this.options.container.getCoordinates().width;
            var b = this.options.container.getCoordinates().height;
            var a = 0;
            var e = 0;
            if (d > c.width) {
                a = (d - c.width) / 2;
            }
            if (b > c.height) {
                e = (b - c.height) / 2;
            }
            if (this.options.center == "xy") {
                this.move(a, e);
            } else {
                if (this.options.center == "x") {
                    this.move(a, c.y);
                } else {
                    if (this.options.center == "y") {
                        this.move(c.x, e);
                    }
                }
            }
        }
        if (this.options.mask) {
            this.maskOverlay.setZIndex(c.zIndex);
        }
    },
    setZindex: function(a) {
        this.panel.setStyle("zIndex", a);
    },
    getZindex: function() {
        return parseInt(this.panel.getStyle("zIndex"));
    },
    show: function(a) {
        a = $merge(this.options, this._fixedSize(a));
        this.options = a;
        delete this.options.max;
        if ((a && a.max) || this.options.autoMax) {
            this._max();
        } else {
            this.resize(a);
        }
        this.panel.setStyle("display", "block");
        if (this.options.icon) {
            this.setIcon(this.options.icon);
        }
        this._rePosition(this.options);
        if (this.options.modal) {
            this.overlay.setZIndex(a.zIndex - 1);
            this.overlay.show();
        }
    },
    hide: function() {
        if (this.options.modal) {
            this.overlay.hide();
        }
        if (this.options.mask) {
            this.maskOverlay.hide();
        }
        this.panel.setStyle("display", "none");
    },
    _max: function() {
        if (this.options.move) {
            this.dragable.detach();
        }
        this._point = {
            x: this.panel.getStyle("left"),
            y: this.panel.getStyle("top")
        };
        this.panel.getElement(".btn_max").setStyle("display", "none");
        this.panel.getElement(".btn_resume").setStyle("display", "block");
        window.addEvent("resize",
        function() {
            this.max();
        }.bind(this));
        this.max();
    },
    max: function() {},
    resume: function() {
        if (this.options.move) {
            this.dragable.attach();
        }
        this.resize();
        this.panel.setStyles({
            left: this._point.x,
            top: this._point.y
        });
        this.panel.getElement(".btn_max").setStyle("display", "block");
        this.panel.getElement(".btn_resume").setStyle("display", "none");
    },
    close: function() {
        var a = {
            stop: false
        };
        this.fireEvent("close", a);
        if (!a.stop) {
            if (this.options.closeAction == "hide") {
                this.hide();
            } else {
                this.destroy();
                if (this.options.modal) {
                    this.overlay.destroy();
                }
                if (this.options.mask) {
                    this.maskOverlay.destroy();
                }
            }
        }
    },
    move: function(a, b) {
        this.panel.setStyles({
            left: a,
            top: b
        });
    }
});
SimpleUI.Window.maxZindex = 0;
SimpleUI.Desktop = {};
SimpleUI.Desktop.Taskbar = {};
SimpleUI.Desktop.Taskbar.AppItemBar = new Class({
    Implements: [Options],
    options: {
        onActive: function(a) {},
        onDeactive: function(a) {}
    },
    initialize: function(b, a) {
        this.setOptions(this.options, a);
        this.appBar = $(b);
        this._items = [];
    },
    addItem: function(a) {
        if (!a.onActive) {
            a.onActive = function() {};
        }
        var d = '<div class="application_item_left"></div><div class="application_item_content"><div class="application_item_content_ico"><img src="' + a.icon + '" width="16px" height="16px"></img></div><div class="application_item_content_text">' + a.title + '</div></div><div class="application_item_right"></div>';
        var b = new Element("div", {
            "class": "application_item",
            id: $ENV.generateId()
        }).injectInside(this.appBar).addEvent("click",
        function() {
            this.activeItem(b.id);
        }.bind(this)).set("html", d);
        var c = {
            id: b.id,
            el: b,
            onActive: a.onActive
        };
        this._items.push(c);
        return c;
    },
    activeItem: function(a) {
        this._items.each(function(b) {
            if (b.id == a) {
                b.el.removeClass("application_item");
                b.el.removeClass("application_item_active");
                b.el.addClass("application_item_active");
                b.onActive();
            } else {
                b.el.removeClass("application_item");
                b.el.removeClass("application_item_active");
                b.el.addClass("application_item");
            }
        }.bind(this));
    },
    removeItem: function(b) {
        var a = null;
        this._items.each(function(c) {
            if (c.id == b) {
                a = c;
            }
        });
        if (a) {
            $(a.el).destroy();
        }
        this._items.erase(a);
        if (this._items[0]) {
            this.activeItem(this._items[0].id);
        }
    }
});
$ENV.dialog = {
    _zIndex: 10000,
    _openIndex: 0,
    _zIndexSeed: 10,
    _createDialog: function() {
        return new SimpleUI.Window({
            center: "xy",
            modal: true,
            min: false,
            max: false,
            move: true,
            title: "",
            closeAction: "hide"
        });
    },
    _dialogs: [],
    _openedDialogs: [],
    _getLastOpenedDialog: function() {
        return this._openedDialogs[this._openedDialogs.length - 1];
    },
    openDialog: function(a, c) {
        if (!c) {
            c = window;
        }
        if (this._dialogs.length <= 0) {
            this._dialogs.push(this._createDialog());
        }
        var b = this._dialogs[0];
        this._dialogs.splice(0, 1);
        this._openIndex++;
        a.zIndex = (this._openIndex * this._zIndexSeed) + this._zIndex;
        if (a.onClose) {
            b.addEvent("close",
            function(d) {
                a.onClose(d);
                this.removeEvent("close", arguments.callee);
            });
        }
        b.show(a);
        a.opener = c;
        b.load(a);
        b.opener = c;
        this._openedDialogs.push(b);
    },
    closeDialog: function() {
        if (this._openedDialogs.length > 0) {
            var b = this._openedDialogs.length;
            var a = this._getLastOpenedDialog();
            if (a.opener) {
                delete a.opener;
            }
            this._openedDialogs.splice(b - 1, 1);
            this._openIndex--;
            a.close();
            this._dialogs.push(a);
        }
    },
    open: function(a, b) {
        if (!b) {
            b = window;
        }
        $ENV.getGlobalEnv().dialog.openDialog(a, b);
    },
    close: function() {
        $ENV.getGlobalEnv().dialog.closeDialog({
            stop: false
        });
    },
    onClose: function(a) {},
    getOpener: function() {
        var a = $ENV.getGlobalEnv().dialog._openedDialogs;
        if (a.length > 0) {
            var b = a[a.length - 1];
            return b.opener;
        }
        return null;
    },
    alert: function(b, a) {
        window.alert(b);
        if (a) {
            a();
        }
    },
    confirm: function(c, b) {
        var a = window.confirm(c);
        if (b) {
            b(a);
        }
    }
};