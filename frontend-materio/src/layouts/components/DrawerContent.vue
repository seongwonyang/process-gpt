<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: 'Master' }" />
        <VerticalNavLink
            :item="{
                title: '거래처',
                to: 'accounts',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '품목',
                to: 'items',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'Purchase' }" />
        <VerticalNavLink
            :item="{
                title: '매입',
                to: 'purchases',
            }"
        />
    </ul>
</template>
