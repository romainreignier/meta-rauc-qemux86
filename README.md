# `meta-rauc-qemu`

This meta has been created in order to demonstrate how to use [meta-rauc](https://github.com/rauc/meta-rauc) on a x86 taget using grub (and maybe later, UEFI directly).

To ease the demonstration, qemu is used.

**WARNING the meta is not finished yet and does not work.**

## Dependencies

  URI: git://git.openembedded.org/bitbake

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

## Patches

For any comments and issue, use the Github issue tab.

For patch, just send a Github Pull Request.

## Table of Contents

1. Adding the meta-rauc-qemu layer to your build

### 1. Adding the meta-rauc-qemu layer to your build

Run

	bitbake-layers add-layer meta-rauc-qemu

Add to `local.conf`:

```
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

