Firebase Cloud Messaging Server Library.

Sample)
<pre>
// Set FcmService.
FcmService fs = new FcmService();
fs.setServerKey(serverKey);		

// Request FCM.
MulticastResult result = fs.send(params);
</pre>
<br/>

FCM Reference Site) <br/>
https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages?hl=ko
