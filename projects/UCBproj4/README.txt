The C code is in a mess. Let's talk about what I acctually do.

Main ways to speed up in my work:
1. SIMD instructions.
2. OMP thread level parallelism to process different pictures.
3. The best way to speed up "volume_get" and "volume_set" is just don't use them. Since these two functions which are too simple to speed up occupied approximately 70% running time in starter code.
4. Rearrange the sequence of layers to increase cache hit rate.