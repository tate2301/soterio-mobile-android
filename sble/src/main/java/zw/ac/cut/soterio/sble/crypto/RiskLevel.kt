package zw.ac.cut.soterio.sble.crypto

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 * Risk level defined for an [TemporaryExposureKey].
 */
@IntDef(
    RiskLevel.RISK_LEVEL_INVALID,
    RiskLevel.RISK_LEVEL_LOWEST,
    RiskLevel.RISK_LEVEL_LOW,
    RiskLevel.RISK_LEVEL_LOW_MEDIUM,
    RiskLevel.RISK_LEVEL_MEDIUM,
    RiskLevel.RISK_LEVEL_MEDIUM_HIGH,
    RiskLevel.RISK_LEVEL_HIGH,
    RiskLevel.RISK_LEVEL_VERY_HIGH,
    RiskLevel.RISK_LEVEL_HIGHEST
)
@Retention(
    RetentionPolicy.SOURCE
)
annotation class RiskLevel {
    companion object {
        const val RISK_LEVEL_INVALID = 0
        const val  RISK_LEVEL_LOWEST = 1
        const val  RISK_LEVEL_LOW = 2
        const val  RISK_LEVEL_LOW_MEDIUM = 3
        const val  RISK_LEVEL_MEDIUM = 4
        const val  RISK_LEVEL_MEDIUM_HIGH = 5
        const val  RISK_LEVEL_HIGH = 6
        const val  RISK_LEVEL_VERY_HIGH = 7
        const val  RISK_LEVEL_HIGHEST = 8
    }
}