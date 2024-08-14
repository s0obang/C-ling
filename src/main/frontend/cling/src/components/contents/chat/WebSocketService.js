import { Client } from '@stomp/stompjs';

const WebSocketService = {
    client: null,

    connect: (roomId, onMessageReceived) => {
        const socket = new WebSocket('ws://13.48.207.238:1234/ws');
        
        WebSocketService.client = new Client({
            webSocketFactory: () => socket,
            connectHeaders: {
                'Content-Type': 'application/json',
            },
            onConnect: () => {
                console.log('Connected to WebSocket');
                WebSocketService.client.subscribe(`/sub/room/${roomId}`, onMessageReceived);
            },
            debug: (str) => {
                console.log(str);
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
        });

        WebSocketService.client.activate();
    },

    disconnect: () => {
        if (WebSocketService.client) {
            WebSocketService.client.deactivate();
            WebSocketService.client = null;
        }
    },

    sendMessage: (roomId, sender, message) => {
        if (WebSocketService.client && WebSocketService.client.connected) {
            WebSocketService.client.publish({
                destination: `/pub/room/${roomId}/chat`,
                body: JSON.stringify({ sender, message }),
            });
        }
    }
};

export default WebSocketService;
