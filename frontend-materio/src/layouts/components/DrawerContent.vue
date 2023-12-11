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
                title: '프로세스 관리',
                to: 'accounts',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '프로세스 실행',
                to: 'items',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'Purchase' }" />
        <VerticalNavLink
            :item="{
                title: '할 일',
                to: 'purchases',
            }"
        />
    </ul>
</template>
