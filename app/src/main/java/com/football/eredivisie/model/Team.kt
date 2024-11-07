package com.football.eredivisie.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Coach(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val name: String,
    val dateOfBirth: String,
    val nationality: String,
    val contract: Contract
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Contract::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(name)
        parcel.writeString(dateOfBirth)
        parcel.writeString(nationality)
        parcel.writeParcelable(contract, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coach> {
        override fun createFromParcel(parcel: Parcel): Coach {
            return Coach(parcel)
        }

        override fun newArray(size: Int): Array<Coach?> {
            return arrayOfNulls(size)
        }
    }
}

data class Contract(
    val start: String,
    val until: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(start)
        parcel.writeString(until)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contract> {
        override fun createFromParcel(parcel: Parcel): Contract {
            return Contract(parcel)
        }

        override fun newArray(size: Int): Array<Contract?> {
            return arrayOfNulls(size)
        }
    }
}

data class Team(
    val id: Int? = null,
    var name: String? = null,
    var shortName: String? = null,
    var tla: String? = null,
    var crest: String? = null,
    var address: String? = null,
    var website: String? = null,
    var founded: String? = null,
    var clubColors: String? = null,
    var venue: String? = null,
    var lastUpdated: Date? = null,
    var squad: List<Player> = emptyList(),
    var coach: Coach? = null // Add this line
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as? Date,
        parcel.createTypedArrayList(Player.CREATOR) ?: emptyList(),
        parcel.readParcelable(Coach::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(shortName)
        parcel.writeString(tla)
        parcel.writeString(crest)
        parcel.writeString(address)
        parcel.writeString(website)
        parcel.writeString(founded)
        parcel.writeString(clubColors)
        parcel.writeString(venue)
        parcel.writeSerializable(lastUpdated)
        parcel.writeTypedList(squad)
        parcel.writeParcelable(coach, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}