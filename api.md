## SpawnerSilk - API

SpawnerSilk allow you to manage plugin data with an API. This section explains how to use the API of SpawnerSilk.

### Methods  

### Get ItemStack from an Entity

```public static ItemStack getSpawner(EntityType entity)```

This method return a spawner of the entity entity. If the entity is not valid or not supported by SpawnerSilk, the method return a default spawner with displayName equals to "????".

To use it, you need to link the SpawnerSilk jar file to your project and call the method through the SpawnerAPI class.
Exemple: `SpawnerAPI.getSpawner(EntityType.SKELETON_HORSE);`

### Get Entity from an ItemStack

```public static EntityType getEntityType(ItemStack is)```

This method return the corresponding EntityType of a spawner. If the entity is not valid or not supported by SpawnerSilk, the method return EntityType.UNKNOW.

To use it, you need to link the SpawnerSilk jar file to your project and call the method through the SpawnerAPI class.
Exemple: `SpawnerAPI.getEntityType(myItemStackSpawner);`

### Get Item stack from entity name

```public static ItemStack stringToCustomItemStack(String mobName, int amount)```

This method return an itemstack of spawner type of the mobName given in parameter 

To use it, you need to link the SpawnerSilk jar file to your project and call the method through the SpawnerAPI class.
Exemple: `SpawnerAPI.stringToCustomItemStack("cat", 1);`

### Notes

If you are a developer and you need more tools from SpawnerSilk to work with. Contact me on Discord.

### Next step
See [contribution part](https://apavarino.github.io/SpawnerSilk/contribution) or go to [home page](https://apavarino.github.io/SpawnerSilk)
