# DaaAsignment
 Implemented algorithms
- MergeSort — recursive sorting algorithm, divide array into halves and merge.
- QuickSort — recursive sorting with pivot and partition.
- Deterministic Select (simplified) — I used `Arrays.sort()` and return k-th element.  
- Closest Pair (simplified)— brute force check of all pairs O(n^2).

All code is inside one `Main.java`.  
Program prints comparisons, swaps and recursion depth for some tests.

 Architecture notes
- Recursion depth is measured with two counters (`enter` and `exit` functions).  
- Comparisons and swaps are counted with global variables.  
- Memory allocations are simple: MergeSort makes temporary arrays, QuickSort swaps elements in place, Select uses Java’s sort, Closest Pair only calculates distances.  

Recurrence analysis

MergeSort  
Recurrence: `T(n) = 2T(n/2) + O(n)` → Master theorem case 2 → result is `Θ(n log n)`.  
It is stable and predictable.  

QuickSort  
Recurrence average case: `T(n) = 2T(n/2) + O(n)` → `Θ(n log n)`.  
Worst case is `Θ(n^2)` if pivot is always bad. Random pivot usually avoids this.  

Deterministic Select  
Full algorithm (median-of-medians) gives `Θ(n)` by recurrence `T(n) = T(n/5) + T(7n/10) + O(n)`.  
I used simple version with `Arrays.sort()`, so complexity is `Θ(n log n)`.  

Closest Pair
Brute force version is `Θ(n^2)`.  
The real divide and conquer version is `Θ(n log n)`. I did not implement it because of complexity.  

 Plots (what should be done)
- Plot **time vs n** for MergeSort and QuickSort.  
- Plot **depth vs n** to show recursion depth growth.  
- Constant factors can change results: cache helps MergeSort, QuickSort can be slower with bad pivot, Java garbage collector (GC) can add noise.  

Summary
- Theory: MergeSort and QuickSort (average) are `Θ(n log n)`, Select is `Θ(n log n)` in my version (should be `Θ(n)`), Closest Pair is `Θ(n^2)` in my version (should be `Θ(n log n)`).  
- Practice: small input sizes may not follow asymptotic rules because of constants.  
- My measurements showed expected growth but with small differences caused by cache and memory allocations.  
- Conclusion: theory gives the trend, but real results depend on implementation and environment.  
