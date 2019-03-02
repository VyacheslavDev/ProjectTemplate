package company.it.moneyapp.network.responce_model

import com.google.gson.annotations.SerializedName

data class DetailsPlacesResponce(
    @SerializedName("html_attributions")
    var htmlAttributions: List<Any> = listOf(),
    @SerializedName("result")
    var result: Result = Result(),
    @SerializedName("status")
    var status: String = "" // OK
) {
    data class Result(
        @SerializedName("address_components")
        var addressComponents: List<AddressComponent> = listOf(),
        @SerializedName("adr_address")
        var adrAddress: String = "", // <span class="street-address">Ленинский пр., 124</span>, <span class="locality">Санкт-Петербург</span>, <span class="country-name">Россия</span>, <span class="postal-code">198207</span>
        @SerializedName("formatted_address")
        var formattedAddress: String = "", // Ленинский пр., 124, Санкт-Петербург, Россия, 198207
        @SerializedName("geometry")
        var geometry: Geometry = Geometry(),
        @SerializedName("icon")
        var icon: String = "", // https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png
        @SerializedName("id")
        var id: String = "", // 4d11b0fb243ab7e36d3a0404031c041755b470d6
        @SerializedName("name")
        var name: String = "", // Ленинский пр., 124
        @SerializedName("place_id")
        var placeId: String = "", // ChIJccz8_e46lkYRjqlqQjReV9k
        @SerializedName("plus_code")
        var plusCode: PlusCode = PlusCode(),
        @SerializedName("reference")
        var reference: String = "", // ChIJccz8_e46lkYRjqlqQjReV9k
        @SerializedName("scope")
        var scope: String = "", // GOOGLE
        @SerializedName("types")
        var types: List<String> = listOf(),
        @SerializedName("url")
        var url: String = "", // https://maps.google.com/?q=%D0%9B%D0%B5%D0%BD%D0%B8%D0%BD%D1%81%D0%BA%D0%B8%D0%B9+%D0%BF%D1%80.,+124,+%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3,+%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F,+198207&ftid=0x46963aeefdfccc71:0xd9575e34426aa98e
        @SerializedName("utc_offset")
        var utcOffset: Int = 0, // 180
        @SerializedName("vicinity")
        var vicinity: String = "" // Санкт-Петербург
    ) {
        data class PlusCode(
            @SerializedName("compound_code")
            var compoundCode: String = "", // V726+WQ Кировский район, Санкт-Петербург, Россия
            @SerializedName("global_code")
            var globalCode: String = "" // 9GFGV726+WQ
        )

        data class Geometry(
            @SerializedName("location")
            var location: Location = Location(),
            @SerializedName("viewport")
            var viewport: Viewport = Viewport()
        ) {
            data class Location(
                @SerializedName("lat")
                var lat: Double = 0.0, // 59.852292
                @SerializedName("lng")
                var lng: Double = 0.0 // 30.261972
            )

            data class Viewport(
                @SerializedName("northeast")
                var northeast: Northeast = Northeast(),
                @SerializedName("southwest")
                var southwest: Southwest = Southwest()
            ) {
                data class Southwest(
                    @SerializedName("lat")
                    var lat: Double = 0.0, // 59.85088861970849
                    @SerializedName("lng")
                    var lng: Double = 0.0 // 30.2606210197085
                )

                data class Northeast(
                    @SerializedName("lat")
                    var lat: Double = 0.0, // 59.8535865802915
                    @SerializedName("lng")
                    var lng: Double = 0.0 // 30.2633189802915
                )
            }
        }

        data class AddressComponent(
            @SerializedName("long_name")
            var longName: String = "", // 198207
            @SerializedName("short_name")
            var shortName: String = "", // 198207
            @SerializedName("types")
            var types: List<String> = listOf()
        )
    }
}