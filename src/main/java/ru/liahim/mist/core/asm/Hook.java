package ru.liahim.mist.core.asm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Р В§РЎвЂљР С•Р В±РЎвЂ№ РЎРѓР Т‘Р ВµР В»Р В°РЎвЂљРЎРЉ Р С�Р ВµРЎвЂљР С•Р Т‘ РЎвЂ¦РЎС“Р С”Р С•Р С�, Р Р…РЎС“Р В¶Р Р…Р С• Р С—Р С•Р Р†Р ВµРЎРѓР С‘РЎвЂљРЎРЉ Р Р…Р В°Р Т‘ Р Р…Р С‘Р С� РЎРЊРЎвЂљРЎС“ Р В°Р Р…Р Р…Р С•РЎвЂљР В°РЎвЂ Р С‘РЎР‹ Р С‘ Р В·Р В°РЎР‚Р ВµР С–Р С‘РЎРѓРЎвЂљРЎР‚Р С‘РЎР‚Р С•Р Р†Р В°РЎвЂљРЎРЉ Р С”Р В»Р В°РЎРѓРЎРѓ РЎРѓ РЎвЂ¦РЎС“Р С”Р С•Р С�.
 * <p/>
 * Р В¦Р ВµР В»Р ВµР Р†Р С•Р в„– Р С”Р В»Р В°РЎРѓРЎРѓ Р С•Р С—РЎР‚Р ВµР Т‘Р ВµР В»РЎРЏР ВµРЎвЂљРЎРѓРЎРЏ Р С—Р ВµРЎР‚Р Р†РЎвЂ№Р С� Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р С� РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘Р В°. Р вЂўРЎРѓР В»Р С‘ РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р в„– Р С�Р ВµРЎвЂљР С•Р Т‘ static, РЎвЂљР С• РЎвЂљРЎС“Р Т‘Р В° Р С—РЎР‚Р С‘Р В»Р ВµРЎвЂљР В°Р ВµРЎвЂљ null,
 * Р С‘Р Р…Р В°РЎвЂЎР Вµ - this.
 * <p/>
 * Р СњР В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В° Р С—Р С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ РЎвЂљР В°Р С”Р С•Р Вµ Р В¶Р Вµ, Р С”Р В°Р С” Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘Р В°, Р Р…Р С• Р ВµР С–Р С• Р С�Р С•Р В¶Р Р…Р С• Р С—Р ВµРЎР‚Р ВµР С•Р С—РЎР‚Р ВµР Т‘Р ВµР В»Р С‘РЎвЂљРЎРЉ РЎвЂЎР ВµРЎР‚Р ВµР В·
 * targetMethod.
 * <p/>
 * Р РЋР С—Р С‘РЎРѓР С•Р С” Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р Р† РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В° Р С•Р С—РЎР‚Р ВµР Т‘Р ВµР В»РЎРЏР ВµРЎвЂљРЎРѓРЎРЏ РЎРѓР С—Р С‘РЎРѓР С”Р С•Р С� Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р Р† РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘Р В°. Р СњРЎС“Р В¶Р Р…Р С• Р Т‘Р С•Р В±Р В°Р Р†Р С‘РЎвЂљРЎРЉ Р Р†РЎРѓР Вµ РЎвЂљР Вµ Р В¶Р Вµ Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚РЎвЂ№
 * Р Р† РЎвЂљР С•Р С� Р В¶Р Вµ Р С—Р С•РЎР‚РЎРЏР Т‘Р С”Р Вµ.
 * <p/>
 * Р вЂ™Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�РЎвЂ№Р в„– РЎвЂљР С‘Р С— РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В° Р С—Р С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ Р Р…Р Вµ РЎС“Р С”Р В°Р В·РЎвЂ№Р Р†Р В°Р ВµРЎвЂљРЎРѓРЎРЏ Р Р†Р С•Р С•Р В±РЎвЂ°Р Вµ. Р СџРЎР‚Р ВµР Т‘Р С—Р С•Р В»Р В°Р С–Р В°Р ВµРЎвЂљРЎРѓРЎРЏ, РЎвЂЎРЎвЂљР С• Р С�Р ВµРЎвЂљР С•Р Т‘Р С•Р Р† РЎРѓ Р С•Р Т‘Р С‘Р Р…Р В°Р С”Р С•Р Р†РЎвЂ№Р С�
 * Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р ВµР С� Р С‘ РЎРѓР С—Р С‘РЎРѓР С”Р С•Р С� Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р Р† Р Р…Р ВµРЎвЂљ. Р вЂўРЎРѓР В»Р С‘ Р Р†РЎРѓРЎвЂ� Р В¶Р Вµ Р Р…РЎС“Р В¶Р Р…Р С• РЎС“Р С”Р В°Р В·Р В°РЎвЂљРЎРЉ, РЎвЂљР С• РЎРЊРЎвЂљР С• Р С�Р С•Р В¶Р Р…Р С• РЎРѓР Т‘Р ВµР В»Р В°РЎвЂљРЎРЉ РЎвЂЎР ВµРЎР‚Р ВµР В· returnType.
 */
@Target(ElementType.METHOD)
public @interface Hook {

    /**
     * Р вЂ”Р В°Р Т‘Р В°Р ВµРЎвЂљ РЎС“РЎРѓР В»Р С•Р Р†Р С‘Р Вµ, Р С—Р С• Р С”Р С•РЎвЂљР С•РЎР‚Р С•Р С�РЎС“ Р С—Р С•РЎРѓР В»Р Вµ Р Р†РЎвЂ№Р В·Р С•Р Р†Р В° РЎвЂ¦РЎС“Р С”Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†РЎвЂ№Р В·Р Р†Р В°Р Р… return.
     * Р вЂўРЎРѓР В»Р С‘ РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р в„– Р С�Р ВµРЎвЂљР С•Р Т‘ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµРЎвЂљ Р Р…Р Вµ void, РЎвЂљР С• Р С—Р С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р ВµР Р…Р С• РЎвЂљР С•, РЎвЂЎРЎвЂљР С• Р Р†Р ВµРЎР‚Р Р…РЎС“Р В» РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘.
     * Р В­РЎвЂљР С• Р С�Р С•Р В¶Р Р…Р С• Р С—Р ВµРЎР‚Р ВµР С•Р С—РЎР‚Р ВµР Т‘Р С‘Р В»Р С‘РЎвЂљРЎРЉ Р Р…Р ВµРЎРѓР С”Р С•Р В»РЎРЉР С”Р С‘Р С�Р С‘ РЎРЊР В»Р ВµР С�Р ВµР Р…РЎвЂљР В°Р С�Р С‘ Р В°Р Р…Р Р…Р С•РЎвЂљР В°РЎвЂ Р С‘Р С‘:
     * returnAnotherMethod, returnNull Р С‘ %type%ReturnConstant.
     */
    ReturnCondition returnCondition() default ReturnCondition.NEVER;

    /**
     * Р вЂ”Р В°Р Т‘Р В°Р ВµРЎвЂљ Р С—РЎР‚Р С‘Р С•РЎР‚Р С‘РЎвЂљР ВµРЎвЂљ РЎвЂ¦РЎС“Р С”Р В°.
     * Р ТђРЎС“Р С”Р С‘ РЎРѓ Р В±Р С•Р В»РЎРЉРЎв‚¬Р С‘Р С� Р С—РЎР‚Р С‘Р С•РЎР‚Р С‘РЎвЂљР ВµРЎвЂљР С•Р С� Р Р†РЎвЂ№Р В·Р В°Р Р†Р В°РЎР‹РЎвЂљРЎРѓРЎРЏ РЎР‚Р В°Р Р…РЎРЉРЎв‚¬Р Вµ.
     */
    HookPriority priority() default HookPriority.NORMAL;

    /**
     * Р вЂ”Р В°Р Т‘Р В°Р ВµРЎвЂљ Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В°.
     * Р СџР С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ Р С‘РЎРѓР С—Р С•Р В»РЎРЉР В·РЎС“Р ВµРЎвЂљРЎРѓРЎРЏ Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘Р В°.
     * Р В­РЎвЂљР В° Р С•Р С—РЎвЂ Р С‘РЎРЏ Р С—Р С•Р В»Р ВµР В·Р Р…Р В°, Р С”Р С•Р С–Р Т‘Р В° Р Р…РЎС“Р В¶Р Р…Р С• Р Р†РЎРѓРЎвЂљР В°Р Р†Р С‘РЎвЂљРЎРЉ РЎвЂ¦РЎС“Р С” Р Р† Р С”Р С•Р Р…РЎРѓРЎвЂљРЎР‚РЎС“Р С”РЎвЂљР С•РЎР‚ Р С‘Р В»Р С‘ Р С‘Р Р…Р С‘РЎвЂ Р С‘Р В°Р В»Р С‘Р В·Р В°РЎвЂ Р С‘РЎР‹ Р С”Р В»Р В°РЎРѓРЎРѓР В°.
     * Р вЂќР В»РЎРЏ Р С”Р С•Р Р…РЎРѓРЎвЂљРЎР‚РЎС“Р С”РЎвЂљР С•РЎР‚Р В° targetMethod Р Т‘Р С•Р В»Р В¶Р ВµР Р… Р В±РЎвЂ№РЎвЂљРЎРЉ "<init>", Р Т‘Р В»РЎРЏ Р С‘Р Р…Р С‘РЎвЂ Р С‘Р В°Р В»Р С‘Р В·Р В°РЎвЂ Р С‘Р С‘ Р С”Р В»Р В°РЎРѓРЎРѓР В° - "<clinit>"
     */
    String targetMethod() default "";

    /**
     * Р вЂ”Р В°Р Т‘Р В°Р ВµРЎвЂљ РЎвЂљР С‘Р С—, Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�РЎвЂ№Р в„– РЎвЂ Р ВµР В»Р ВµР Р†РЎвЂ№Р С� Р С�Р ВµРЎвЂљР С•Р Т‘Р С•Р С�.
     * Р РЋ РЎвЂљР С•РЎвЂЎР С”Р С‘ Р В·РЎР‚Р ВµР Р…Р С‘РЎРЏ JVM Р С�Р С•Р С–РЎС“РЎвЂљ Р В±РЎвЂ№РЎвЂљРЎРЉ Р С�Р ВµРЎвЂљР С•Р Т‘РЎвЂ№, Р С”Р С•РЎвЂљР С•РЎР‚РЎвЂ№Р Вµ Р С•РЎвЂљР В»Р С‘РЎвЂЎР В°РЎР‹РЎвЂљРЎРѓРЎРЏ РЎвЂљР С•Р В»РЎРЉР С”Р С• Р Р†Р С•Р В·РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�РЎвЂ№Р С� РЎвЂљР С‘Р С—Р С•Р С�.
     * Р СњР В° Р С—РЎР‚Р В°Р С”РЎвЂљР С‘Р С”Р Вµ Р С”Р С•Р С�Р С—Р С‘Р В»РЎРЏРЎвЂљР С•РЎР‚РЎвЂ№ РЎвЂљР В°Р С”Р С‘РЎвЂ¦ Р С�Р ВµРЎвЂљР С•Р Т‘Р С•Р Р† Р Р…Р Вµ Р С–Р ВµР Р…Р ВµРЎР‚Р С‘РЎР‚РЎС“РЎР‹РЎвЂљ, Р Р…Р С• Р Р† Р Р…Р ВµР С”Р С•РЎвЂљР С•РЎР‚РЎвЂ№РЎвЂ¦ РЎРѓР В»РЎС“РЎвЂЎР В°РЎРЏРЎвЂ¦ Р С•Р Р…Р С‘
     * Р С�Р С•Р С–РЎС“РЎвЂљ Р Р†РЎРѓРЎвЂљРЎР‚Р ВµРЎвЂљР С‘РЎвЂљРЎРЉРЎРѓРЎРЏ (Р Р…Р В°Р С—РЎР‚Р С‘Р С�Р ВµРЎР‚, РЎРЊРЎвЂљР С• Р С�Р С•Р В¶Р Р…Р С• РЎРѓР Т‘Р ВµР В»Р В°РЎвЂљРЎРЉ Р С—РЎР‚Р С‘ Р С•Р В±РЎвЂћРЎС“РЎРѓР С”Р В°РЎвЂ Р С‘Р С‘ РЎвЂЎР ВµРЎР‚Р ВµР В· ProGuard)
     * Р вЂўРЎРѓР В»Р С‘ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�РЎвЂ№Р в„– РЎвЂљР С‘Р С— Р Р…Р Вµ РЎС“Р С”Р В°Р В·Р В°Р Р…, РЎвЂљР С• РЎвЂ¦РЎС“Р С” Р С—РЎР‚Р С‘Р С�Р ВµР Р…РЎРЏР ВµРЎвЂљРЎРѓРЎРЏ Р С” Р С—Р ВµРЎР‚Р Р†Р С•Р С�РЎС“ Р С�Р ВµРЎвЂљР С•Р Т‘РЎС“, Р С—Р С•Р Т‘РЎвЂ¦Р С•Р Т‘РЎРЏРЎвЂ°Р ВµР С�РЎС“
     * Р С—Р С• Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘РЎР‹ Р С‘ РЎРѓР С—Р С‘РЎРѓР С”РЎС“ Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р Р†.
     *
     * Р С›РЎРѓР Р…Р С•Р Р†Р Р…Р С•Р в„– Р С—РЎР‚Р ВµР Т‘Р С—Р С•Р В»Р В°Р С–Р В°Р ВµР С�РЎвЂ№Р в„– РЎРѓР С—Р С•РЎРѓР С•Р В± Р С‘РЎРѓР С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°Р Р…Р С‘РЎРЏ РЎРЊРЎвЂљР С•Р С–Р С• Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р В° - Р Р†Р С�Р ВµРЎРѓРЎвЂљР Вµ РЎРѓ createMethod = true.
     * Р вЂ™ РЎРЊРЎвЂљР С•Р С� РЎРѓР В»РЎС“РЎвЂЎР В°Р Вµ РЎРѓР С•Р В·Р Т‘Р В°Р Р…Р Р…РЎвЂ№Р в„– Р С�Р ВµРЎвЂљР С•Р Т‘ Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р С—Р С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ Р С‘Р С�Р ВµРЎвЂљРЎРЉ РЎвЂљР С•РЎвЂљ Р В¶Р Вµ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�РЎвЂ№Р в„– РЎвЂљР С‘Р С—, РЎвЂЎРЎвЂљР С• Р С‘ РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘,
     * Р В° РЎРѓ Р С—Р С•Р С�Р С•РЎвЂ°РЎРЉРЎР‹ РЎРЊРЎвЂљР С•Р С–Р С• Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р В° РЎРЊРЎвЂљР С• Р С�Р С•Р В¶Р Р…Р С• Р С‘Р В·Р С�Р ВµР Р…Р С‘РЎвЂљРЎРЉ.
     *
     * Р Р€Р С”Р В°Р В·РЎвЂ№Р Р†Р В°РЎвЂљРЎРЉ Р Р…РЎС“Р В¶Р Р…Р С• Р С—Р С•Р В»Р Р…Р С•Р Вµ Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ Р С”Р В»Р В°РЎРѓРЎРѓР В°: java.lang.String, void, int Р С‘ РЎвЂљ.Р Т‘.
     */
    String returnType() default "";

    /**
     * Р СџР С•Р В·Р Р†Р С•Р В»РЎРЏР ВµРЎвЂљ Р Р…Р Вµ РЎвЂљР С•Р В»РЎРЉР С”Р С• Р Р†РЎРѓРЎвЂљР В°Р Р†Р В»РЎРЏРЎвЂљРЎРЉ РЎвЂ¦РЎС“Р С”Р С‘ Р Р† РЎРѓРЎС“РЎвЂ°Р ВµРЎРѓРЎвЂљР Р†РЎС“РЎР‹РЎвЂ°Р С‘Р Вµ Р С�Р ВµРЎвЂљР С•Р Т‘РЎвЂ№, Р Р…Р С• Р С‘ Р Т‘Р С•Р В±Р В°Р Р†Р В»РЎРЏРЎвЂљРЎРЉ Р Р…Р С•Р Р†РЎвЂ№Р Вµ. Р В­РЎвЂљР С• Р С�Р С•Р В¶Р ВµРЎвЂљ Р С—Р С•Р Р…Р В°Р Т‘Р С•Р В±Р С‘РЎвЂљРЎРЉРЎРѓРЎРЏ,
     * Р С”Р С•Р С–Р Т‘Р В° Р Р…РЎС“Р В¶Р Р…Р С• Р С—Р ВµРЎР‚Р ВµР С•Р С—РЎР‚Р ВµР Т‘Р ВµР В»Р С‘РЎвЂљРЎРЉ Р С�Р ВµРЎвЂљР С•Р Т‘ РЎРѓРЎС“Р С—Р ВµРЎР‚Р С”Р В»Р В°РЎРѓРЎРѓР В°. Р вЂўРЎРѓР В»Р С‘ РЎРѓРЎС“Р С—Р ВµРЎР‚-Р С�Р ВµРЎвЂљР С•Р Т‘ Р Р…Р В°Р в„–Р Т‘Р ВµР Р…, РЎвЂљР С• РЎвЂљР ВµР В»Р С• Р С–Р ВµР Р…Р ВµРЎР‚Р С‘РЎР‚РЎС“Р ВµР С�Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В°
     * Р С—РЎР‚Р ВµР Т‘РЎРѓРЎвЂљР В°Р Р†Р В»РЎРЏР ВµРЎвЂљ РЎРѓР С•Р В±Р С•Р в„– Р Р†РЎвЂ№Р В·Р С•Р Р† РЎРѓРЎС“Р С—Р ВµРЎР‚-Р С�Р ВµРЎвЂљР С•Р Т‘Р В°. Р пїЅР Р…Р В°РЎвЂЎР Вµ РЎРЊРЎвЂљР С• Р С—РЎР‚Р С•РЎРѓРЎвЂљР С• Р С—РЎС“РЎРѓРЎвЂљР С•Р в„– Р С�Р ВµРЎвЂљР С•Р Т‘ Р С‘Р В»Р С‘ return false/0/null Р Р† Р В·Р В°Р Р†Р С‘РЎРѓР С‘Р С�Р С•РЎРѓРЎвЂљР С‘
     * Р С•РЎвЂљ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµР С�Р С•Р С–Р С• РЎвЂљР С‘Р С—Р В°.
     */
    boolean createMethod() default false;


    /**
     * Р СџР С•Р В·Р Р†Р С•Р В»РЎРЏР ВµРЎвЂљ Р С•Р В±РЎР‰РЎРЏР Р†Р С‘РЎвЂљРЎРЉ РЎвЂ¦РЎС“Р С” "Р С•Р В±РЎРЏР В·Р В°РЎвЂљР ВµР В»РЎРЉР Р…РЎвЂ№Р С�" Р Т‘Р В»РЎРЏ Р В·Р В°Р С—РЎС“РЎРѓР С”Р В° Р С‘Р С–РЎР‚РЎвЂ№. Р вЂ™ РЎРѓР В»РЎС“РЎвЂЎР В°Р Вµ Р Р…Р ВµРЎС“Р Т‘Р В°РЎвЂЎР С‘ Р Р†Р С• Р Р†РЎР‚Р ВµР С�РЎРЏ Р Р†РЎРѓРЎвЂљР В°Р Р†Р С”Р С‘ РЎвЂљР В°Р С”Р С•Р С–Р С• РЎвЂ¦РЎС“Р С”Р В°
     * Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р…Р Вµ Р С—РЎР‚Р С•РЎРѓРЎвЂљР С• Р Р†РЎвЂ№Р Р†Р ВµР Т‘Р ВµР Р…Р С• РЎРѓР С•Р С•Р В±РЎвЂ°Р ВµР Р…Р С‘Р Вµ Р Р† Р В»Р С•Р С–, Р В° Р С”РЎР‚Р В°РЎв‚¬Р Р…Р ВµРЎвЂљРЎРѓРЎРЏ Р С‘Р С–РЎР‚Р В°.
     */
    boolean isMandatory() default false;

    /**
     * Р СџР С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ РЎвЂ¦РЎС“Р С” Р Р†РЎРѓРЎвЂљР В°Р Р†Р В»РЎРЏР ВµРЎвЂљРЎРѓРЎРЏ Р Р† Р Р…Р В°РЎвЂЎР В°Р В»Р С• РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В°.
     * Р вЂўРЎРѓР В»Р С‘ РЎС“Р С”Р В°Р В·Р В°РЎвЂљРЎРЉ Р В·Р Т‘Р ВµРЎРѓРЎРЉ true, РЎвЂљР С• Р С•Р Р… Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†РЎРѓРЎвЂљР В°Р Р†Р В»Р ВµР Р… Р Р† Р С”Р С•Р Р…РЎвЂ Р Вµ Р С‘ Р С—Р ВµРЎР‚Р ВµР Т‘ Р С”Р В°Р В¶Р Т‘РЎвЂ№Р С� Р Р†РЎвЂ№Р В·Р С•Р Р†Р С•Р С� return.
     */
    boolean injectOnExit() default false;

    /**
     * Р СџР С• РЎС“Р С�Р С•Р В»РЎвЂЎР В°Р Р…Р С‘РЎР‹ РЎвЂ¦РЎС“Р С” Р Р†РЎРѓРЎвЂљР В°Р Р†Р В»РЎРЏР ВµРЎвЂљРЎРѓРЎРЏ Р Р† Р Р…Р В°РЎвЂЎР В°Р В»Р С• РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С–Р С• Р С�Р ВµРЎвЂљР С•Р Т‘Р В°.
     * Р вЂўРЎРѓР В»Р С‘ РЎС“Р С”Р В°Р В·Р В°РЎвЂљРЎРЉ Р В·Р Т‘Р ВµРЎРѓРЎРЉ true, РЎвЂљР С• Р С•Р Р… Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†РЎРѓРЎвЂљР В°Р Р†Р В»Р ВµР Р… Р Р† Р Р…Р В°РЎвЂЎР В°Р В»Р Вµ РЎС“Р С”Р В°Р В·Р В°Р Р…Р Р…Р С•Р в„– РЎРѓРЎвЂљРЎР‚Р С•Р С”Р С‘.
     * Р пїЅРЎРѓР С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљРЎРЉ Р Р…Р Вµ РЎР‚Р ВµР С”Р С•Р С�Р ВµР Р…Р Т‘РЎС“Р ВµРЎвЂљРЎРѓРЎРЏ, Р С—Р С•РЎвЂљР С•Р С�РЎС“ РЎвЂЎРЎвЂљР С•:
     * 1) Р вЂ™РЎРѓРЎвЂљР В°Р Р†Р С‘РЎвЂљРЎРЉ Р С�Р С•Р В¶Р Р…Р С• РЎвЂљР С•Р В»РЎРЉР С”Р С• Р Р…Р В° РЎРѓРЎвЂљРЎР‚Р С•Р С”РЎС“ РЎРѓ Р С‘Р Р…РЎРѓРЎвЂљРЎР‚РЎС“Р С”РЎвЂ Р С‘Р ВµР в„–
     * 2) Р СљР С•Р В¶Р ВµРЎвЂљ Р вЂ™Р СњР вЂўР вЂ”Р С’Р СџР СњР С› РЎРѓР В»Р С•Р С�Р В°РЎвЂљРЎРЉРЎРѓРЎРЏ (Р Р…Р В°Р С—РЎР‚Р С‘Р С�Р ВµРЎР‚, Р С•РЎвЂљ РЎвЂљР С•Р С–Р С•, РЎвЂЎРЎвЂљР С• Р С”Р В°Р С”Р С•Р в„–-Р Р…Р С‘Р В±РЎС“Р Т‘РЎРЉ Р С•Р С—РЎвЂљР С‘РЎвЂћР В°Р в„–Р Р… Р С—Р С•Р Т‘Р С�Р ВµР Р…Р С‘РЎвЂљ Р С”Р В»Р В°РЎРѓРЎРѓ РЎвЂ Р ВµР В»Р С‘Р С”Р С•Р С�)
     */
    @Deprecated int injectOnLine() default -1;

    /**
     * Р вЂўРЎРѓР В»Р С‘ РЎС“Р С”Р В°Р В·Р В°Р Р…Р С• РЎРЊРЎвЂљР С• Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ, РЎвЂљР С• Р С—РЎР‚Р С‘ Р Р†РЎвЂ№Р В·Р С•Р Р†Р Вµ return Р Р† РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С� Р С�Р ВµРЎвЂљР С•Р Т‘Р Вµ Р В±РЎС“Р Т‘Р ВµРЎвЂљ РЎРѓР Р…Р В°РЎвЂЎР В°Р В»Р В° Р Р†РЎвЂ№Р В·Р Р†Р В°Р Р… РЎРЊРЎвЂљР С•РЎвЂљ Р С�Р ВµРЎвЂљР С•Р Т‘.
     * Р С›Р Р… Р Т‘Р С•Р В»Р В¶Р ВµР Р… Р Р…Р В°РЎвЂ¦Р С•Р Т‘Р С‘РЎвЂљРЎРЉРЎРѓРЎРЏ Р Р† РЎвЂљР С•Р С� Р В¶Р Вµ Р С”Р В»Р В°РЎРѓРЎРѓР Вµ Р С‘ Р С‘Р С�Р ВµРЎвЂљРЎРЉ РЎвЂљР С•РЎвЂљ Р В¶Р Вµ РЎРѓР С—Р С‘РЎРѓР С•Р С” Р С—Р В°РЎР‚Р В°Р С�Р ВµРЎвЂљРЎР‚Р С•Р Р†, РЎвЂЎРЎвЂљР С• Р С‘ РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘.
     * Р вЂ™ Р С‘РЎвЂљР С•Р С–Р Вµ Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р ВµР Р…Р С• Р В·Р Р…Р В°РЎвЂЎР ВµР Р…Р С‘Р Вµ, Р С”Р С•РЎвЂљР С•РЎР‚Р С•Р Вµ Р Р†Р ВµРЎР‚Р Р…РЎвЂ�РЎвЂљ РЎРЊРЎвЂљР С•РЎвЂљ Р С�Р ВµРЎвЂљР С•Р Т‘.
     */
    String returnAnotherMethod() default "";

    /**
     * Р вЂўРЎРѓР В»Р С‘ true, РЎвЂљР С• Р С—РЎР‚Р С‘ Р Р†РЎвЂ№Р В·Р С•Р Р†Р Вµ return Р Р† РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С� Р С�Р ВµРЎвЂљР С•Р Т‘Р Вµ Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р ВµР Р…Р С• null
     */
    boolean returnNull() default false;

    /**
     * Р вЂўРЎРѓР В»Р С‘ Р С•Р С—РЎР‚Р ВµР Т‘Р ВµР В»Р ВµР Р…Р В° Р С•Р Т‘Р Р…Р В° Р С‘Р В· РЎРЊРЎвЂљР С‘РЎвЂ¦ Р С”Р С•Р Р…РЎРѓРЎвЂљР В°Р Р…РЎвЂљ, РЎвЂљР С• Р С•Р Р…Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р ВµР Р…Р В° Р С—РЎР‚Р С‘ Р Р†РЎвЂ№Р В·Р С•Р Р†Р Вµ return Р Р† РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р С� Р С�Р ВµРЎвЂљР С•Р Т‘Р Вµ
     */

    boolean booleanReturnConstant() default false;

    byte byteReturnConstant() default 0;

    short shortReturnConstant() default 0;

    int intReturnConstant() default 0;

    long longReturnConstant() default 0L;

    float floatReturnConstant() default 0.0F;

    double doubleReturnConstant() default 0.0D;

    char charReturnConstant() default 0;

    String stringReturnConstant() default "";

    @Target(ElementType.PARAMETER)
    @interface LocalVariable {
        int value();
    }

    /**
     * Р СџР ВµРЎР‚Р ВµРЎвЂ¦Р Р†Р В°РЎвЂљРЎвЂ№Р Р†Р В°Р ВµРЎвЂљ Р В·Р Р…Р В°РЎвЂЎР ВµР Р…Р С‘Р Вµ, Р С”Р С•РЎвЂљР С•РЎР‚Р С•Р Вµ Р С‘Р В·Р Р…Р В°РЎвЂЎР В°Р В»РЎРЉР Р…Р С• РЎв‚¬Р В»Р С• Р Р† return, Р С‘ Р С—Р ВµРЎР‚Р ВµР Т‘Р В°Р ВµРЎвЂљ Р ВµР С–Р С• РЎвЂ¦РЎС“Р С”-Р С�Р ВµРЎвЂљР С•Р Т‘РЎС“.
     * Р вЂњР С•Р Р†Р С•РЎР‚РЎРЏ Р В±Р С•Р В»Р ВµР Вµ РЎвЂћР С•РЎР‚Р С�Р В°Р В»РЎРЉР Р…Р С•, Р С—Р ВµРЎР‚Р ВµР Т‘Р В°Р ВµРЎвЂљ Р С—Р С•РЎРѓР В»Р ВµР Т‘Р Р…Р ВµР Вµ Р В·Р Р…Р В°РЎвЂЎР ВµР Р…Р С‘Р Вµ Р Р† РЎРѓРЎвЂљР В°Р С”Р Вµ.
     * Р СљР С•Р В¶Р Р…Р С• Р С‘РЎРѓР С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљРЎРЉ РЎвЂљР С•Р В»РЎРЉР С”Р С• Р С”Р С•Р С–Р Т‘Р В° injectOnExit() == true Р С‘ РЎвЂ Р ВµР В»Р ВµР Р†Р С•Р в„– Р С�Р ВµРЎвЂљР С•Р Т‘ Р Р†Р С•Р В·Р Р†РЎР‚Р В°РЎвЂ°Р В°Р ВµРЎвЂљ Р Р…Р Вµ void.
     */
    @Target(ElementType.PARAMETER)
    @interface ReturnValue {}
}
