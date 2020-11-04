package alserdar89.onewaymessage.models

import java.util.*

 class NotificationModel {

    var theMessage:String? = ""
    var senderName:String?= ""
    var senderUID:String?=""
    var senderLittleID:String?=""
    var senderPic:String?= ""
    var token:String?=""
    var messageDate:String? = ""
    var messageTime:String? = ""

    constructor()

    constructor(theMessage:String? ,
                senderName:String? ,
                senderUID:String?,
                senderLittleID:String?,
                senderPic:String? ,
                token:String? ,
                messageDate:String? ,
                messageTime:String?)
    {
        this.theMessage = theMessage
        this.senderName = senderName
        this.senderUID = senderUID
        this.senderLittleID = senderLittleID
        this.senderPic = senderPic
        this.token=token
        this.messageDate = messageDate
        this.messageTime = messageTime
    }
}