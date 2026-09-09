package ru.rustore.remoteconfigexample

enum class UpdateBehaviourType(val displayName: String) {
    ACTUAL("Actual"),
    DEFAULT("Default"),
    SNAPSHOT("Snapshot");

    companion object {
        fun parse(value: String?): UpdateBehaviourType? {
            if (value.isNullOrBlank()) return null
            return values().firstOrNull { it.name == value || it.displayName == value }
        }
    }
}
