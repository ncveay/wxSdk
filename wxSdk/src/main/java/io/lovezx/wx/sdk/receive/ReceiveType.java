package io.lovezx.wx.sdk.receive;

import java.util.Map;

public enum ReceiveType {

    TEXT {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onText(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            TextReceive receive = new TextReceive();
            receive.setContent(xmlMap.get("Content"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }
        
    }, IMAGE {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onImage(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            ImageReceive receive = new ImageReceive();
            receive.setMediaId(xmlMap.get("MediaId"));
            receive.setPicUrl(xmlMap.get("PicUrl"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }

    }, VOICE {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onVoice(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            VoiceReceive receive = new VoiceReceive();
            receive.setMediaId(xmlMap.get("MediaId"));
            receive.setFormat(xmlMap.get("Format"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setRecognition(xmlMap.get("Recognition"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }
    }, VIDEO {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onVoice(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            VideoReceive receive = new VideoReceive();
            receive.setMediaId(xmlMap.get("MediaId"));
            receive.setThumbMediaId(xmlMap.get("ThumbMediaId"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }
    }, SHORTVIDEO {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onShortVideo(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            VideoReceive receive = new VideoReceive();
            receive.setMediaId(xmlMap.get("MediaId"));
            receive.setThumbMediaId(xmlMap.get("ThumbMediaId"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }

    }, LOCATION {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onLocation(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            LocationReceive receive = new LocationReceive();
            receive.setLocation_x(xmlMap.get("Location_X"));
            receive.setLocation_y(xmlMap.get("Location_Y"));
            receive.setScale(xmlMap.get("Scale"));
            receive.setLabel(xmlMap.get("Label"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }

    }, LINK {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
            return processor.onLink(context);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            LinkReceive receive = new LinkReceive();
            receive.setUrl(xmlMap.get("Url"));
            receive.setTitle(xmlMap.get("Title"));
            receive.setDescription(xmlMap.get("Description"));
            receive.setMsgId(xmlMap.get("MsgId"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }
    }, EVENT {
        @Override
        public Resource processor(ReceiveContext context,
                ReceiveProcessor processor) {
           EventType type = context.getEventType();
           return type.processor(context, processor);
        }

        @Override
        ReceiveResource parseResource(Map<String, String> xmlMap) {
            EventType event = EventType.valueOf(xmlMap.get("Event").toUpperCase());
            return event.parseResource(xmlMap);
        }
    };
    
    public abstract Resource processor(ReceiveContext context, ReceiveProcessor processor);
    abstract ReceiveResource parseResource(Map<String, String> xmlMap);
    
    public static enum EventType{
        SUBSCRIBE {
            @Override
            public Resource processor(ReceiveContext context,
                    ReceiveProcessor processor) {
                return processor.onSubscribe(context);
            }
        }, UNSUBSCRIBE{

			@Override
			public Resource processor(ReceiveContext context,
					ReceiveProcessor processor) {
				return processor.onUnSubscribe(context);
			}
        	
        }, SCAN {
            @Override
            public Resource processor(ReceiveContext context,
                    ReceiveProcessor processor) {
                return processor.onScan(context);
            }
        }, LOCATION {
            @Override
            public Resource processor(ReceiveContext context,
                    ReceiveProcessor processor) {
                return processor.onLocationReport(context);
            }
            ReceiveResource parseResource(Map<String, String> xmlMap){
                LocationReportReceive receive = new LocationReportReceive();
                receive.setLocation_x(xmlMap.get("Latitude"));
                receive.setLocation_y(xmlMap.get("Longitude"));
                receive.setPrecision(xmlMap.get("Precision"));
                receive.setMsgType(xmlMap.get("MsgType"));
                return receive;
            }
        }, CLICK {
            @Override
            public Resource processor(ReceiveContext context,
                    ReceiveProcessor processor) {
                return processor.onClick(context);
            }
        }, VIEW {
            @Override
            public Resource processor(ReceiveContext context,
                    ReceiveProcessor processor) {
                return processor.onView(context);
            }
        },
        KF_CREATE_SESSION{

			@Override
			public Resource processor(ReceiveContext context, ReceiveProcessor processor) {
				processor.onDKFcreate(context);
				return null;
			}
        	
        },KF_CLOSE_SESSION {

			@Override
			public Resource processor(ReceiveContext context,
					ReceiveProcessor processor) {
				 return processor.onDKFclose(context);
			}
        	
        },MASSSENDJOBFINISH{
        	@Override
        	public Resource processor(ReceiveContext context, ReceiveProcessor processor) {
        		return processor.endMassSendJob(context);
        	}
        };
        public abstract Resource processor(ReceiveContext context, ReceiveProcessor processor);
        
        ReceiveResource parseResource(Map<String, String> xmlMap){
            EventKeyReceive receive = new EventKeyReceive();
            receive.setKey(xmlMap.get("EventKey"));
            receive.setMsgType(xmlMap.get("MsgType"));
            return receive;
        }
    }
}
