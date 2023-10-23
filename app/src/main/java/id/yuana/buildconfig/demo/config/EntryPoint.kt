package id.yuana.buildconfig.demo.config

/**
 * describe [EntryPoint] will be hold entry point after splash
 */
class EntryPoint(
    val activityClass: Class<*>,
    val email: String,
    val password: String
)