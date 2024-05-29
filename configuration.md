## SpawnerSilk - Configuration

Make sure you have [installed](https://apavarino.github.io/SpawnerSilk/installation) the plugin before reading this step.

This section describes how to configure SpawnerSilk through  the`config.yml` file


Option | Value | Default | Description
--- | --- | --- | ---
`auto-update` | boolean  | true | Enable or disable plugin auto update
`need-silk-touch-to-destroy` | boolean  | true |  Only player with silkTouch can destroy spawner (need spawnersilk.minespawner permission)
`need-silk-touch` | boolean  | true | Player need silk touch to collect spawner
`pickaxe-mode` | 0..6  | 5 | 0 : Player can get spawner by breaking it no matter how<br>1 : Player need to break the spawner with wooden pickaxe or better<br>2 : Player need to break the spawner with stone pickaxe or better<br>3 : Player need to break the spawner with iron pickaxe or better<br>4 : Player need to break the spawner with golden pickaxe or better<br>5 : Player need to break the spawner with diamond pickaxe or better<br>6 : Player need to break the spawner with netherite pickaxe
`drop-chance` | 0..100  | 100 | Percentage of chance that player drop a spawner by mining it
`drop-egg-chance` | 0..100  | 100 | Percentage of chance that player drop an egg (only for drop-mode = 1)
`drop-mod` | 0..1  | 0 | 0 : Spawner of the creature is dropped<br>1 : Basic spawner is dropped with egg of the creature type (depends on drop-egg-chance)
`spawners-can-be-modified-by-egge` | true/false | true | Player can change spawner type with eggs
`explosion-drop-chance` | 0..100  | 10 | Percentage of chance that player drop a spawner by an explosionchance)
`drop-to-inventory` | true/false | false | Drops go directly to inventory
`use-egg` | true/false | true | Egg is consume when used on the spawner

### Next step
See [commands & permissions part](https://apavarino.github.io/SpawnerSilk/commands-and-perms) or go to [home page](https://apavarino.github.io/SpawnerSilk)


