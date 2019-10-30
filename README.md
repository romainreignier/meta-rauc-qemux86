# `meta-rauc-qemu`

This meta has been created in order to demonstrate how to use [meta-rauc](https://github.com/rauc/meta-rauc) on a x86 taget.

To ease the demonstration, [QEMU](https://www.qemu.org/) is used.

**WARNING the meta is not finished yet and does not work.**

## Dependencies

  URI: git://git.openembedded.org/bitbake

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: warrior

  URI: git://git.openembedded.org/meta-openembedded
  layers: meta-oe
  branch: warrior

## Patches

For any comments and issue, use the Github issue tab.

For patch, just send a Github Pull Request.

## Table of Contents

1. Adding the meta-rauc-qemu layer to your build
2. Use `systemd-boot`
3. Use Grub

### 1. Adding the meta-rauc-qemu layer to your build

Run

	bitbake-layers add-layer meta-rauc-qemu

### 2. Use `systemd-boot`

Add to `local.conf`:

```
# systemd-boot version
MACHINE_FEATURES += "pcbios efi"
DISTRO_FEATURES += "efi"
IMAGE_FSTYPES += "wic"
WKS_FILE = "qemux86-systemd-boot-efi.wks"
EFI_PROVIDER = "systemd-boot"
IMAGE_INSTALL_append = " \
                systemd-boot \
                efibootmgr \
"
EXTRA_IMAGEDEPENDS += "ovmf"

# Only use systemd as init
DISTRO_FEATURES += "systemd"
DISTRO_FEATURES_remove = "sysvinit"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""
VIRTUAL-RUNTIME_syslog = ""
VIRTUAL-RUNTIME_init_manager = "systemd"

# Install ssh server
IMAGE_INSTALL_append = " dropbear"
```

Start qemu with:

```
runqemu wic nographic ovmf
```

### 3. Use Grub

Add to `local.conf`:

```
MACHINE ?= "qemux86-64"
MACHINE_FEATURES += "pcbios efi"
IMAGE_FSTYPES += "wic"
WKS_FILE = "qemux86-grub-efi.wks"
EFI_PROVIDER = "grub-efi"
IMAGE_INSTALL_append = " \
                grub \
                grub-efi \
"
EXTRA_IMAGEDEPENDS += "ovmf"
PREFERRED_RPROVIDER_virtual/grub-bootconf = "rauc-qemu-grubconf"
```

Start qemu with:

```
runqemu wic nographic ovmf
```

Inspired from [this Poky patch](https://lists.yoctoproject.org/pipermail/yocto/2018-November/043242.html).

