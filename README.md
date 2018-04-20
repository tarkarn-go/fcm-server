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

Q&A) <br/>
Q. Where to get Server Key?
  1. Click the settings icon/cog wheel next to your project name at the top of the new Firebase Console
  2. Click Project settings
  3. Click on the Cloud Messaging tab
  4. The key is right under Server Key
