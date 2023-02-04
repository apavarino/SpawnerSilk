## SpawnerSilk - Commands & Permissions
Make sure you have [installed](https://apavarino.github.io/SpawnerSilk/installation) the plugin before reading this step.

This section describes commands and permissions provided by the plugin

Command | Permission | Description
--- | --- | --- |
`/givespawner <Player> <Type> [Amount]` | `spawnersilk.givespawner` | Give a specific spawner to a specific player
`/editspawner <Params> <Value>` | `spawnersilk.editspawner` | Edit a spawner
`/sps reload` | `spawnersilk.reload` | Reload the plugin
 -- | `spawnersilk.minespawner` | Allows player to mine spawner

###  `/editspawner` command details

To use is this command you need to look at the spawner you want to edit.
The command can take in parameter several different options :

- `spawnRange` : Set the new spawn range
- `spawnCount` : Set how many mobs attempt to spawn.
- `maxNearbyEntities` : Set the maximum number of similar entities that are allowed to be within spawning range of this spawner
- `requiredPlayerRange` : Set the maximum distance (squared) a player can be in order for this spawner to be active.
- `delay` : Set the spawner's delay.
- `maxSpawnDelay` : Set the maximum spawn delay amount (in ticks)
- `minSpawnDelay` : Set the minimum spawn delay amount (in ticks)

For each command a number is required after the option to set the new value.
Example : 
```
/editspawner requiredPlayerRange 5
```
After this command, the spawner will start working when the player is five blocks or less of the spawner.


### Next step
See [troubleshooting part](https://apavarino.github.io/SpawnerSilk/troubleshooting) or go to [home page](https://apavarino.github.io/SpawnerSilk)


