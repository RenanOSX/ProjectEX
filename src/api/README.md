# `src/api` — vendored third-party API stubs

This source set contains compile-only API stubs for mods ProjectEX
soft-depends on via Forge `@Optional`. There are **no Maven artifacts**
for these packages — this directory is the sole compile source.

## Live (do not move, rename, or delete)

The package names are an external contract — other mods and the game
reference these fully-qualified names.

- `baubles.api` (`IBauble`, `BaubleType`, `BaublesApi`) — implemented by
  13+ items (`RepairTalisman`, `TimeWatch`, rings, amulets…), all guarded
  with `@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")`
  and `Loader.isModLoaded("Baubles")` checks. Loaded reflectively against
  the real Baubles mod at runtime (`BaublesApi` targets
  `baubles.common.lib.PlayerHandler`, which lives outside this repo).
- `thaumcraft.api` (`IGoggles`, `nodes.IRevealer`) — implemented by
  `RMArmor` and `GemHelmet` behind `@Optional` + `modid = "Thaumcraft"`.
- `invtweaks.api.container.ChestContainer` — annotation used by
  `AlchBagContainer` and `AlchChestContainer`.

## Retained but unreferenced

The remaining `invtweaks.api` types (`InventoryContainer`,
`IgnoreContainer`, `ContainerSection`, `ContainerSectionCallback`,
`InvTweaksAPI`, `IItemTree{,Item,Category,Listener}`, `SortingMethod`)
have no importers in `src/main`. They are kept for source compatibility
only. Do not add new usages; removal needs a full build + jar-compare
because third parties could compile against them.
