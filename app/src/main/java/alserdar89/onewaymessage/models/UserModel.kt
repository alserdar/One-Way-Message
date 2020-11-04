package alserdar89.onewaymessage.models

import android.net.Uri
import java.util.*

class UserModel {

    var theUID: String? = ""
    var userName:String? = ""
    var handle:String? = ""
    var handleLowerCase:String? = ""
    var gender:String? = ""
    var avatar: String? = null
    var country:String? = ""
    var town:String? = ""
    var age:String? = ""
    var birthDate:String? = ""
    var relationship:String? =""
    var aboutUser:String? = ""
    var linkUser:String? = ""
    var id: String? = ""
    var littleId:String?= ""
    var token:String?= ""
    var gmail:String? = ""

    var online: Boolean? = false
    var star:Boolean? = false
    var globallyPerson:Boolean? = false
    var locallyPerson:Boolean? = false
    var paid:Boolean? = false
    var sus:Boolean? = false
    var privateAccount:Boolean? = false
    var hasPic:Boolean? = false

    var reportAccount:Int? = 0
    var getX:Int? = 0

    var createAccount = Date()
    var lastJoin = Date()



    constructor()

    constructor(
        theUID: String?,
        userName:String?,
        handle:String? ,
        handleLowerCase:String?,
        gender: String?,
        avatar:String? ,
        country: String?,
        town: String?,
        age: String?,
        birthDate: String?,
        relationship: String?,
        aboutUser: String?,
        linkUser: String?,
        id: String?,
        littleId: String?,
        token: String?,
        gmail: String?,
        online: Boolean?,
        star: Boolean?,
        globallyPerson: Boolean?,
        locallyPerson: Boolean?,
        paid: Boolean?,
        sus: Boolean?,
        privateAccount: Boolean?,
        hasPic: Boolean?,
        reportAccount:Int?,
        getX:Int?,
        createAccount:Date ,
        lastJoin:Date) {

        this.theUID =theUID
        this.userName = userName
        this.handle=handle
        this.handleLowerCase = handleLowerCase
        this.gender = gender
        this.avatar = avatar
        this.country = country
        this.town = town
        this.age = age
        this.birthDate = birthDate
        this.relationship = relationship
        this.aboutUser = aboutUser
        this.linkUser = linkUser
        this.id = id
        this.littleId = littleId
        this.token = token
        this.gmail = gmail
        this.online = online
        this.star = star
        this.globallyPerson=globallyPerson
        this.locallyPerson=locallyPerson
        this.paid = paid
        this.sus = sus
        this.privateAccount = privateAccount
        this.hasPic = hasPic

        this.reportAccount = reportAccount
        this.getX = getX

        this.createAccount = createAccount
        this.lastJoin = lastJoin

    }
}