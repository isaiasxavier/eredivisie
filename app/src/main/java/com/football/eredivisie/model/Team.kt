package com.football.eredivisie.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

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
    var squad: List<Player> = emptyList() // Adicione esta linha
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
        parcel.createTypedArrayList(Player.CREATOR) ?: emptyList() // Adicione esta linha
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
        parcel.writeTypedList(squad) // Adicione esta linha
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