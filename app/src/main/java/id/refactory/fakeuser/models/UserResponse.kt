package id.refactory.fakeuser.models

data class UserResponse(
	val results: List<ResultsItem>,
	val info: Info
)

data class Timezone(
	val offset: String,
	val description: String
)

data class Street(
	val number: Int,
	val name: String
)

data class Picture(
	val thumbnail: String,
	val large: String,
	val medium: String
)

data class Location(
	val country: String,
	val city: String,
	val street: Street,
	val timezone: Timezone,
	val postcode: String,
	val coordinates: Coordinates,
	val state: String
)

data class Name(
	val last: String,
	val title: String,
	val first: String
)

data class Id(
	val name: String,
	val value: String
)

data class Registered(
	val date: String,
	val age: Int
)

data class Coordinates(
	val latitude: String,
	val longitude: String
)

data class Info(
	val seed: String,
	val page: Int,
	val results: Int,
	val version: String
)

data class Login(
	val sha1: String,
	val password: String,
	val salt: String,
	val sha256: String,
	val uuid: String,
	val username: String,
	val md5: String
)

data class ResultsItem(
	val nat: String,
	val gender: String,
	val phone: String,
	val dob: Dob,
	val name: Name,
	val registered: Registered,
	val location: Location,
	val id: Id,
	val login: Login,
	val cell: String,
	val email: String,
	val picture: Picture
)

data class Dob(
	val date: String,
	val age: Int
)

