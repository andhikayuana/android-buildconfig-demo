package id.yuana.buildconfig.demo.data

interface DataRepository {

    fun login(email: String, password: String): Boolean

    fun alreadyLogin(): Boolean

    fun logout()

    class Impl : DataRepository {

        private val users = mapOf(
            "jarjit@spam4.me" to "12345678",
            "ismail@spam4.me" to "12345678"
        )

        var loginStatus: Map<String, Boolean> = mapOf("UNKNOWN" to false)

        override fun login(email: String, password: String): Boolean {
            val exists = users.containsKey(email)
            if (exists.not()) return false
            val result = users[email] == password
            if (result.not()) return false
            loginStatus = mapOf(email to true)
            return result
        }

        override fun alreadyLogin(): Boolean = loginStatus.values.first()

        override fun logout() {
            loginStatus = mapOf("UNKNOWN" to false)
        }

    }
}