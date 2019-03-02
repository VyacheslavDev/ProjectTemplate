package company.it.moneyapp.network.responce_model

import com.google.gson.annotations.SerializedName

data class PlacesResponce(
    @SerializedName("candidates")
    var candidates: List<Candidate> = listOf(),
    @SerializedName("status")
    var status: String = "" // OK
) {
    data class Candidate(
        @SerializedName("formatted_address")
        var formattedAddress: String = "", // Ленинский пр., 124, Санкт-Петербург, Россия, 198207
        @SerializedName("place_id")
        var placeId: String = "" // ChIJccz8_e46lkYRjqlqQjReV9k
    )
}