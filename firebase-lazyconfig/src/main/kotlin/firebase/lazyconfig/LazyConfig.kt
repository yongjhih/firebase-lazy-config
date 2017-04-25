package firebase.lazyconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private fun valueNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("Value", id, desc)
private fun byteArrayNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("ByteArray", id, desc)
private fun longNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("Long", id, desc)
private fun intNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("Integer", id, desc)
private fun doubleNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("Double", id, desc)
private fun stringNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("String", id, desc)
private fun boolNotFound(id: String, desc: KProperty<*>): Nothing =
    notFound("Boolean", id, desc)
private fun notFound(type:String, id: String, desc: KProperty<*>): Nothing =
    throw IllegalStateException("$type ID $id for '${desc.name}' not found.")

fun <T> firebaseValue(id: String)
    = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getValue(id) ?: valueNotFound(id, desc) }

fun <T> firebaseByteArray(id: String)
        = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getByteArray(id) ?: byteArrayNotFound(id, desc) }

fun <T> firebaseLong(id: String)
    = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getLong(id) ?: longNotFound(id, desc) }

fun <T> firebaseDouble(id: String)
    = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getDouble(id) ?: doubleNotFound(id, desc) }

fun <T> firebaseString(id: String)
    = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getString(id) ?: stringNotFound(id, desc) }

fun <T> firebaseBool(id: String)
    = Lazy { t: T, desc -> FirebaseRemoteConfig.getInstance().getBoolean(id) ?: boolNotFound(id, desc) }

// Like Kotlin's lazy delegate but the initializer gets the target and metadata passed to it
class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) : ReadOnlyProperty<T, V> {
  private object EMPTY
  private var value: Any? = EMPTY

  override fun getValue(thisRef: T, property: KProperty<*>): V {
    if (value == EMPTY) {
      value = initializer(thisRef, property)
    }
    @Suppress("UNCHECKED_CAST")
    return value as V
  }
}
