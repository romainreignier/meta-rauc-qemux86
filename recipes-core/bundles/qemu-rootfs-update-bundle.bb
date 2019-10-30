SUMMARY = "RAUC bundle for core-image-minimal on qemux86-64"

inherit bundle

BUNDLE_NAME = "${BUNDLE_BASENAME}-${MACHINE}-${DISTRO_VERSION}-${DATETIME}"

RAUC_BUNDLE_COMPATIBLE ?= "${MACHINE}"

RAUC_BUNDLE_SLOTS ?= "rootfs"

RAUC_SLOT_rootfs ?= "core-image-minimal"
RAUC_SLOT_rootfs[fstype] ?= "ext4"

RAUC_KEY_FILE ?= "${COREBASE}/meta-rauc-qemu/files/development-1.key.pem"
RAUC_CERT_FILE ?= "${COREBASE}/meta-rauc-qemu/files/development-1.cert.pem"
