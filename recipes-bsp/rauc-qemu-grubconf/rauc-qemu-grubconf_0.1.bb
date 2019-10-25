SUMMARY = "Grub configuration file to use with RAUC"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

# When changing the virtual/grub-bootconf then you need to -c cleanall the grub-efi recipe
RPROVIDES_${PN} += "virtual/grub-bootconf"

SRC_URI += " \
    file://grub.cfg \
    file://grubenv \
    "

S = "${WORKDIR}"

do_install[depends] = "virtual/kernel:do_deploy"

do_install() {
	install -d ${D}/boot
	install -d ${D}/boot/EFI
	install -d ${D}/boot/EFI/BOOT
	install grub.cfg ${D}/boot/EFI/BOOT/grub.cfg
	install grubenv ${D}/boot/EFI/BOOT/grubenv
	install ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} ${D}/${GRUB_KERNEL_IMAGE}
}

FILES_${PN} = " \
        /boot/EFI/BOOT/grub.cfg \
        /boot/EFI/BOOT/grubenv \
        /${KERNEL_IMAGETYPE} \
"

